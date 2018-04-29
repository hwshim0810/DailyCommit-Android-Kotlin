package xyz.laziness.dailycommit.ui.base.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection


abstract class BaseFragment : Fragment() {

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

    fun getBaseActivity() = parentActivity

    private fun injection() = AndroidSupportInjection.inject(this)
}