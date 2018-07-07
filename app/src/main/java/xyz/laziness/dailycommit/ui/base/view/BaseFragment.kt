package xyz.laziness.dailycommit.ui.base.view

import android.content.Context
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ProgressBar
import dagger.android.support.AndroidSupportInjection
import xyz.laziness.dailycommit.R


abstract class BaseFragment : Fragment(), BaseView {

    private lateinit var parentActivity: BaseActivity

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        parentActivity = context as BaseActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injection()
        setHasOptionsMenu(false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    fun getBaseActivity() = parentActivity

    private fun injection() = AndroidSupportInjection.inject(this)

    abstract fun initUI()

    override fun showBackButtonToast(backMessageCode: Int) = getBaseActivity().showBackButtonToast(backMessageCode)

    override fun showErrorMessage(@StringRes resId: Int) = getBaseActivity().showErrorMessage(resId)

    override fun finishView() = getBaseActivity().finishView()

    override fun isEqualFragmentByTag(@IdRes fragmentFrame: Int, tag: String): Boolean =
            parentActivity.isEqualFragmentByTag(fragmentFrame, tag)

    override fun showProgress() {
        getBaseActivity().showProgress()
    }

    override fun hideProgress() {
        getBaseActivity().hideProgress()
    }

    override fun setProgressBar(progressBar: ProgressBar) {
        getBaseActivity().setProgressBar(progressBar)
    }
}