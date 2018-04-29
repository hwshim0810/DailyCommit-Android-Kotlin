package xyz.laziness.dailycommit.ui.base.presenter

import xyz.laziness.dailycommit.ui.base.view.BaseView


interface BaseContract<V: BaseView> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

    fun isLogin(): Boolean

    fun logout()
}