package xyz.laziness.dailycommit.ui.modules.splash.presenter

import xyz.laziness.dailycommit.ui.base.presenter.BaseContract
import xyz.laziness.dailycommit.ui.modules.splash.view.SplashView


interface SplashContract<V: SplashView> : BaseContract<V> {

    fun chooseStartActivity(): Unit?

}