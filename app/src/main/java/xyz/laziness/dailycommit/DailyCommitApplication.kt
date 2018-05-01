package xyz.laziness.dailycommit

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import xyz.laziness.dailycommit.di.component.DaggerAppComponent
import javax.inject.Inject


class DailyCommitApplication : Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = activityInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}