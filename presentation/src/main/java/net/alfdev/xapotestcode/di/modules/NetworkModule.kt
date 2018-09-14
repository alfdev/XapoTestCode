package net.alfdev.xapotestcode.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import net.alfdev.xapotestcode.data.network.interceptors.AuthenticationInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Module
class NetworkModule(private val baseUrl: String, private val authToken: String) {
    @Provides
    @Singleton
    fun provideInterceptors(): ArrayList<Interceptor> {
        var interceptors = arrayListOf<Interceptor>()

        // logging interceptor
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        interceptors.add(interceptor)
        interceptors.add(AuthenticationInterceptor(authToken))

        return interceptors
    }

    @Provides
    @Singleton
    fun provideHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()

        interceptors?.forEach { builder.addInterceptor(it) }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
    }
}