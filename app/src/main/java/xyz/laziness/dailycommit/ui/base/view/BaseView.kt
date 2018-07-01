package xyz.laziness.dailycommit.ui.base.view

import android.support.annotation.IdRes


interface BaseView {

    fun showBackButtonToast(backMessageCode: Int)

    fun showErrorMessage()

    fun finishView()

    fun isEqualFragmentByTag(@IdRes fragmentFrame: Int, tag: String): Boolean

}