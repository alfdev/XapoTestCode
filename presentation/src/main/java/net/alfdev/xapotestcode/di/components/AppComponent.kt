package net.alfdev.xapotestcode.di.components

import dagger.Component
import dagger.android.AndroidInjectionModule
import net.alfdev.xapotestcode.App
import net.alfdev.xapotestcode.di.modules.ActivitiesModule
import net.alfdev.xapotestcode.di.modules.AppModule
import net.alfdev.xapotestcode.di.modules.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, NetworkModule::class, ActivitiesModule::class])
interface AppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun plus(module: AppModule): Builder
        fun plus(module: NetworkModule): Builder

        fun build(): AppComponent
    }
}