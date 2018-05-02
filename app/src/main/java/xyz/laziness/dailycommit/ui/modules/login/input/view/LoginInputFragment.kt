package xyz.laziness.dailycommit.ui.modules.login.input.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login_input.*
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.ui.base.view.BaseFragment
import xyz.laziness.dailycommit.ui.modules.login.input.interactor.LoginInputInteractor
import xyz.laziness.dailycommit.ui.modules.login.input.presenter.LoginInputPresenter
import xyz.laziness.dailycommit.utils.AppConstants
import xyz.laziness.dailycommit.utils.BundleConstants
import javax.inject.Inject


class LoginInputFragment : BaseFragment(), LoginInputView {

    companion object {

        internal const val TAG = "LoginInputFragment"

        fun getInstance(): LoginInputFragment = LoginInputFragment()

    }

    @Inject
    internal  lateinit var presenter: LoginInputPresenter<LoginInputView, LoginInputInteractor>

    private lateinit var loginMethod: AppConstants.LoginMethod

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_login_input, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initUI() {
        toggleInputVisible(arguments?.getBoolean(BundleConstants.INPUT_DISPLAY) ?: true)
    }

    fun setBundle(bundle: Bundle) { arguments = bundle }

    private fun toggleInputVisible(isBasic: Boolean) {
        inputPassword.visibility = if (isBasic) {View.VISIBLE} else {View.GONE}
        inputAccessToken.visibility = if (isBasic) {View.GONE} else {View.VISIBLE}
    }
}