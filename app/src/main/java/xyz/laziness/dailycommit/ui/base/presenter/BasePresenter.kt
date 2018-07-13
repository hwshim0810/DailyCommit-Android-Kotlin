package xyz.laziness.dailycommit.ui.base.presenter

import android.support.annotation.StringRes
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractor
import xyz.laziness.dailycommit.ui.base.view.BaseView


interface BasePresenter<V: BaseView, I: BaseInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

    fun onBackPressed()

    fun onError(@StringRes resId: Int = R.string.unknown_error_msg): Unit?

}