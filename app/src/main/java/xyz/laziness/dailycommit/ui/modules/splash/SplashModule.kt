package xyz.laziness.dailycommit.ui.modules.splash

import dagger.Module
import dagger.Provides
import xyz.laziness.dailycommit.ui.modules.splash.presenter.SplashPresenter
import xyz.laziness.dailycommit.ui.modules.splash.view.SplashView


@Module
class SplashModule {

    @Provides
    internal fun provideSplashPresenter(splashPresenter: SplashPresenter<SplashView>) :
            SplashPresenter<SplashView> = splashPresenter
}