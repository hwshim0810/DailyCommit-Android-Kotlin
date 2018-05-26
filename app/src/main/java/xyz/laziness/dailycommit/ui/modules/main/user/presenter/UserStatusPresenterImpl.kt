package xyz.laziness.dailycommit.ui.modules.main.user.presenter

import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenterImpl
import xyz.laziness.dailycommit.ui.modules.main.user.interactor.UserStatusInteractor
import xyz.laziness.dailycommit.ui.modules.main.user.view.UserStatusView
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import javax.inject.Inject


class UserStatusPresenterImpl<V: UserStatusView, I: UserStatusInteractor>
@Inject internal constructor(interactor: I,
                             schedulerHelper: SchedulerHelper,
                             disposable: CompositeDisposable):
        BasePresenterImpl<V, I>(interactor = interactor, schedulerHelper = schedulerHelper, compositeDisposable = disposable),
        UserStatusPresenter<V, I> {


}