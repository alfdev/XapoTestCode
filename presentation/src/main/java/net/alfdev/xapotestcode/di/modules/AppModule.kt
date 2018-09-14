package net.alfdev.xapotestcode.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import net.alfdev.xapotestcode.App
import javax.inject.Singleton

@Module
class AppModule(private val application: App) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application.applicationContext
}