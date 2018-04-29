package xyz.laziness.dailycommit.ui.base.presenter

import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractor
import xyz.laziness.dailycommit.ui.base.view.BaseView
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

}