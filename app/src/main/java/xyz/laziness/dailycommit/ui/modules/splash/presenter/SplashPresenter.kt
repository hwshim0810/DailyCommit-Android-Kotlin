package xyz.laziness.dailycommit.ui.modules.splash.presenter

import xyz.laziness.dailycommit.ui.base.presenter.BasePresenter
import xyz.laziness.dailycommit.ui.modules.splash.interactor.SplashInteractor
import xyz.laziness.dailycommit.ui.modules.splash.view.SplashView


interface SplashPresenter<V: SplashView, I: SplashInteractor> : BasePresenter<V, I> {

    fun chooseStartActivity(): Unit?

    fun isLogin(): Boolean

}