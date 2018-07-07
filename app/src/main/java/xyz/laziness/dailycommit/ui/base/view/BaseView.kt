package xyz.laziness.dailycommit.ui.base.view

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import xyz.laziness.dailycommit.R


interface BaseView {

    fun showBackButtonToast(backMessageCode: Int)

    fun showErrorMessage(@StringRes resId: Int = R.string.unknown_error_msg)

    fun finishView()

    fun isEqualFragmentByTag(@IdRes fragmentFrame: Int, tag: String): Boolean

}