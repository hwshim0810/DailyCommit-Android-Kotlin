package xyz.laziness.dailycommit.ui.modules.main.friends.presenter

import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenterImpl
import xyz.laziness.dailycommit.ui.modules.main.friends.interactor.FriendsStatusInteractor
import xyz.laziness.dailycommit.ui.modules.main.friends.view.FriendsStatusView
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import javax.inject.Inject


class FriendsStatusPresenterImpl<V: FriendsStatusView, I: FriendsStatusInteractor>
@Inject internal constructor(interactor: I,
                             schedulerHelper: SchedulerHelper,
                             disposable: CompositeDisposable):
        BasePresenterImpl<V, I>(interactor = interactor, schedulerHelper = schedulerHelper, compositeDisposable = disposable),
        FriendsStatusPresenter<V, I> {


}