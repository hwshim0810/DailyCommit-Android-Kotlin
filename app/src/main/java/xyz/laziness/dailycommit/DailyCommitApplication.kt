package xyz.laziness.dailycommit

import android.app.Activity
import android.app.Application
import android.content.BroadcastReceiver
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasBroadcastReceiverInjector
import xyz.laziness.dailycommit.di.component.DaggerAppComponent
import javax.inject.Inject


class DailyCommitApplication : Application(), HasActivityInjector, HasBroadcastReceiverInjector {

    @Inject
    internal lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    internal lateinit var broadcastReceiverInjector: DispatchingAndroidInjector<BroadcastReceiver>

    override fun activityInjector() = activityInjector

    override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver> = broadcastReceiverInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}