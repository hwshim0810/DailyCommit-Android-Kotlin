package xyz.laziness.dailycommit.ui.base.presenter

import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractor
import xyz.laziness.dailycommit.ui.base.view.BaseView


interface BasePresenter<V: BaseView, I: BaseInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

    fun onBackPressed()

    fun onError(): Unit?

}