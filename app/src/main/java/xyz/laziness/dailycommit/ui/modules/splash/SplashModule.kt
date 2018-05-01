package xyz.laziness.dailycommit.ui.modules.splash

import dagger.Module
import dagger.Provides
import xyz.laziness.dailycommit.ui.modules.splash.interactor.SplashInteractor
import xyz.laziness.dailycommit.ui.modules.splash.interactor.SplashInteractorImpl
import xyz.laziness.dailycommit.ui.modules.splash.presenter.SplashPresenter
import xyz.laziness.dailycommit.ui.modules.splash.presenter.SplashPresenterImpl
import xyz.laziness.dailycommit.ui.modules.splash.view.SplashView


@Module
class SplashModule {

    @Provides
    internal fun provideSplashPresenter(splashPresenter: SplashPresenterImpl<SplashView, SplashInteractor>) :
            SplashPresenter<SplashView, SplashInteractor> = splashPresenter

    @Provides
    internal fun provideSplashInteractor(splashInteractor: SplashInteractorImpl):
            SplashInteractor = splashInteractor

}