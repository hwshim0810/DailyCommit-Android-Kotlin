package xyz.laziness.dailycommit.ui.modules.login.presenter

import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenterImpl
import xyz.laziness.dailycommit.ui.modules.login.interactor.LoginInteractor
import xyz.laziness.dailycommit.ui.modules.login.view.LoginView
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import javax.inject.Inject


class LoginPresenterImpl<V: LoginView, I: LoginInteractor>
@Inject internal constructor(interactor: I,
                             schedulerHelper: SchedulerHelper,
                             disposable: CompositeDisposable) :
        BasePresenterImpl<V, I>(interactor = interactor, schedulerHelper = schedulerHelper, compositeDisposable = disposable),
        LoginPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        setLoginMainDisplay()
    }

    override fun setLoginMainDisplay() = getView()?.openLoginChooseFragment()

}