package xyz.laziness.dailycommit.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xyz.laziness.dailycommit.ui.modules.login.LoginModule
import xyz.laziness.dailycommit.ui.modules.login.choice.LoginChoiceProvider
import xyz.laziness.dailycommit.ui.modules.login.input.LoginInputProvider
import xyz.laziness.dailycommit.ui.modules.login.view.LoginActivity
import xyz.laziness.dailycommit.ui.modules.main.MainModule
import xyz.laziness.dailycommit.ui.modules.main.view.MainActivity
import xyz.laziness.dailycommit.ui.modules.splash.SplashModule
import xyz.laziness.dailycommit.ui.modules.splash.view.SplashActivity


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(SplashModule::class)])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [(LoginModule::class), (LoginChoiceProvider::class), (LoginInputProvider::class)])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun bindMainActivity(): MainActivity

}