package xyz.laziness.dailycommit.ui.modules.login.input.presenter

import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenterImpl
import xyz.laziness.dailycommit.ui.modules.login.input.interactor.LoginInputInteractor
import xyz.laziness.dailycommit.ui.modules.login.input.view.LoginInputView
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import javax.inject.Inject


class LoginInputPresenterImpl<V: LoginInputView, I: LoginInputInteractor>
@Inject internal constructor(interactor: I,
                             schedulerHelper: SchedulerHelper,
                             disposable: CompositeDisposable) :
        BasePresenterImpl<V, I>(interactor = interactor, schedulerHelper = schedulerHelper, compositeDisposable = disposable),
        LoginInputPresenter<V, I>