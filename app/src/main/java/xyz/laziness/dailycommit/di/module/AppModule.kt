package xyz.laziness.dailycommit.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import xyz.laziness.dailycommit.data.preference.AppPreference
import xyz.laziness.dailycommit.data.preference.BasePreference
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideAppPref(appPreference: AppPreference): BasePreference = appPreference

}