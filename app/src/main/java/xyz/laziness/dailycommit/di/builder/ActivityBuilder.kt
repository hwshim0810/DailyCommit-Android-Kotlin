package xyz.laziness.dailycommit.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xyz.laziness.dailycommit.ui.modules.splash.SplashModule
import xyz.laziness.dailycommit.ui.modules.splash.view.SplashActivity


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(SplashModule::class)])
    abstract fun bindSplashActivity(): SplashActivity
}