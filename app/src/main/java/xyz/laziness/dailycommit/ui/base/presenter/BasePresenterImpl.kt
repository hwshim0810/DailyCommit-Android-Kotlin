package xyz.laziness.dailycommit.ui.base.presenter

import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractor
import xyz.laziness.dailycommit.ui.base.view.BaseView
import xyz.laziness.dailycommit.utils.AppConstants
import xyz.laziness.dailycommit.utils.io.SchedulerHelper


abstract class BasePresenterImpl<V: BaseView, I: BaseInteractor>
    internal constructor(protected var interactor: I?,
                         protected val schedulerHelper: SchedulerHelper,
                         protected val compositeDisposable: CompositeDisposable): BasePresenter<V, I> {

    private var view: V? = null
    private val isViewAttached: Boolean
        get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        interactor = null
        view = null
    }

    override fun getView(): V? = view

    private val backButtonSubject: Subject<Long> = BehaviorSubject.createDefault(0L).toSerialized()

    private val backButtonSubjectDisposable =
            compositeDisposable.add(backButtonSubject.toFlowable(BackpressureStrategy.BUFFER)
                    .compose(schedulerHelper.ioToMainFlowableScheduler())
                    .buffer(2, 1)
                    .map { it[0] to it[1] }
                    .subscribe({ value ->
                        if (value.second - value.first < 2000) getView()?.finishView()
                        else getView()?.showBackButtonToast(AppConstants.BACK_BTN_SUCCESS)
                    },{ getView()?.showBackButtonToast(AppConstants.BACK_BTN_ERROR) })
            )

    override fun onBackPressed() = backButtonSubject.onNext(System.currentTimeMillis())

    override fun onError() = getView()?.showErrorMessage()

}