package net.alfdev.xapotestcode.di.modules

import dagger.Module
import dagger.Provides
import net.alfdev.xapotestcode.data.network.ServiceApi
import net.alfdev.xapotestcode.data.repository.ProjectRepository
import net.alfdev.xapotestcode.data.repository.ProjectRepositoryImpl
import net.alfdev.xapotestcode.di.scopes.ProjectsScope
import retrofit2.Retrofit

@Module
class RepositoriesModule {

    @Provides
    @ProjectsScope
    fun provideServiceApi(retrofit: Retrofit): ServiceApi = retrofit.create(ServiceApi::class.java)

    @Provides
    @ProjectsScope
    fun providesRepository(api: ServiceApi): ProjectRepository {
        return ProjectRepositoryImpl(api)
    }
}