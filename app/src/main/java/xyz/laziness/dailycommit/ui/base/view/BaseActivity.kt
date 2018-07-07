package xyz.laziness.dailycommit.ui.base.view

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dagger.android.AndroidInjection
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.utils.AppConstants


abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injection()
    }

    private fun injection() = AndroidInjection.inject(this)

    override fun onBackPressed() = onViewBackPressed()

    abstract fun onViewBackPressed()

    override fun showBackButtonToast(backMessageCode: Int) {
        // Prepare to override success message
        when (backMessageCode) {
            AppConstants.BACK_BTN_SUCCESS -> Toast.makeText(this, getString(R.string.back_pressed_msg), Toast.LENGTH_SHORT).show()
            AppConstants.BACK_BTN_ERROR -> Toast.makeText(this, getString(R.string.back_pressed_error_msg), Toast.LENGTH_SHORT).show()
        }
    }

    override fun showErrorMessage(@StringRes resId: Int) = Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()

    override fun finishView() = finish()

    override fun isEqualFragmentByTag(@IdRes fragmentFrame: Int, tag: String): Boolean =
            supportFragmentManager.findFragmentById(fragmentFrame)?.tag == tag

    fun superOnBackPressed() = super.onBackPressed()

}