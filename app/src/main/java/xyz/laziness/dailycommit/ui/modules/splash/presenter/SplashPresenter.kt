package xyz.laziness.dailycommit.ui.modules.splash.presenter

import xyz.laziness.dailycommit.ui.base.presenter.BasePresenter
import xyz.laziness.dailycommit.ui.modules.splash.view.SplashView


class SplashPresenter<V: SplashView> : BasePresenter<V>(), SplashContract<V> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        chooseStartActivity()
    }

    override fun chooseStartActivity() = getView()?.let {
        if (isLogin())
            it.startMainActivity()
        else
            it.startLoginActivity()
    }

}