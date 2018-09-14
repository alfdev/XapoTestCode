package net.alfdev.xapotestcode

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.alfdev.xapotestcode.di.components.AppComponent
import net.alfdev.xapotestcode.di.components.DaggerAppComponent
import net.alfdev.xapotestcode.di.modules.AppModule
import net.alfdev.xapotestcode.di.modules.NetworkModule
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .plus(AppModule(this))
                .plus(NetworkModule("https://api.github.com/", BuildConfig.GITHUB_AUTH_TOKEN))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}