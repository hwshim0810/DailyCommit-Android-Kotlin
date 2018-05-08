package xyz.laziness.dailycommit.ui.modules.main.presenter

import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenterImpl
import xyz.laziness.dailycommit.ui.modules.main.interactor.MainInteractor
import xyz.laziness.dailycommit.ui.modules.main.view.MainView
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import javax.inject.Inject


class MainPresenterImpl<V: MainView, I: MainInteractor>
@Inject internal constructor(interactor: I,
                             schedulerHelper: SchedulerHelper,
                             disposable: CompositeDisposable) :
        BasePresenterImpl<V, I>(interactor = interactor, schedulerHelper = schedulerHelper, compositeDisposable = disposable),
        MainPresenter<V, I>
