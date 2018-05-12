package xyz.laziness.dailycommit.ui.base.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import dagger.android.support.AndroidSupportInjection


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    fun getBaseActivity() = parentActivity

    private fun injection() = AndroidSupportInjection.inject(this)

    abstract fun initUI()

    override fun showBackButtonToast(backMessageCode: Int) = getBaseActivity().showBackButtonToast(backMessageCode)

    override fun showErrorMessage() = getBaseActivity().showErrorMessage()

    override fun finishView() = getBaseActivity().finishView()

}