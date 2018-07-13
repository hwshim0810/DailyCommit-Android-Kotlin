package xyz.laziness.dailycommit

import android.app.Activity
import android.app.Application
import android.content.BroadcastReceiver
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasBroadcastReceiverInjector
import okhttp3.OkHttpClient
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

        initNetworkLogger()
    }

    private fun initNetworkLogger() {
        if (BuildConfig.DEBUG) {
            val okHttpClient = OkHttpClient()
                    .newBuilder()
                    .addNetworkInterceptor(HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    .build()

            AndroidNetworking.initialize(applicationContext, okHttpClient)
        }
    }
}