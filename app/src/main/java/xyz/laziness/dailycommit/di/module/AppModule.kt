package xyz.laziness.dailycommit.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.data.network.github.GitHubApiHelper
import xyz.laziness.dailycommit.data.network.github.GitHubApi
import xyz.laziness.dailycommit.di.qualifiers.PreferenceInfo
import xyz.laziness.dailycommit.data.preference.AppPreference
import xyz.laziness.dailycommit.data.preference.BasePreference
import xyz.laziness.dailycommit.utils.AppConstants
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @PreferenceInfo
    internal fun providePrefFileName(): String = AppConstants.PREF_NAME

    @Provides
    @Singleton
    internal fun provideAppPref(appPreference: AppPreference): BasePreference = appPreference

    @Provides
    @Singleton
    internal fun provideGithubApiHelper(gitHubApiHelper: GitHubApiHelper): GitHubApi = gitHubApiHelper

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerHelper(): SchedulerHelper = SchedulerHelper()

}