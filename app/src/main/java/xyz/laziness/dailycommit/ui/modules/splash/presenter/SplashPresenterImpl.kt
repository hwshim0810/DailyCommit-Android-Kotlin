package xyz.laziness.dailycommit.ui.modules.splash.presenter

import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenterImpl
import xyz.laziness.dailycommit.ui.modules.splash.interactor.SplashInteractor
import xyz.laziness.dailycommit.ui.modules.splash.view.SplashView
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import javax.inject.Inject


class SplashPresenterImpl<V: SplashView, I: SplashInteractor>
    @Inject internal constructor(interactor: I,
                                 schedulerHelper: SchedulerHelper,
                                 disposable: CompositeDisposable) :
        BasePresenterImpl<V, I>(interactor = interactor, schedulerHelper = schedulerHelper, compositeDisposable = disposable),
        SplashPresenter<V, I> {

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

    override fun isLogin(): Boolean {
        interactor?.let { return it.isLogin() }
        return false
    }
}