package xyz.laziness.dailycommit.ui.modules.login.input.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_login_input.*
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.ui.base.view.BaseFragment
import xyz.laziness.dailycommit.ui.modules.login.input.interactor.LoginInputInteractor
import xyz.laziness.dailycommit.ui.modules.login.input.presenter.LoginInputPresenter
import xyz.laziness.dailycommit.ui.modules.login.view.LoginActivity
import xyz.laziness.dailycommit.utils.AppConstants
import xyz.laziness.dailycommit.utils.BundleConstants
import javax.inject.Inject


class LoginInputFragment : BaseFragment(), LoginInputView {

    companion object {

        internal const val TAG = "LoginInputFragment"

        fun getInstance(): LoginInputFragment = LoginInputFragment()

    }

    @Inject
    internal lateinit var presenter: LoginInputPresenter<LoginInputView, LoginInputInteractor>

    private var isBasic: Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_login_input, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
    }

    override fun initUI() {
        isBasic = arguments?.getBoolean(BundleConstants.INPUT_DISPLAY) ?: true

        editTextUsername.requestFocus()
        toggleInputVisible(isBasic)
        setOnclickListeners()
    }

    override fun showLoginError(errCode: Int) {
        (getBaseActivity() as? LoginActivity)?.showLoginError(errCode)
    }

    override fun showTwoFactorInput() {
        Toast.makeText(context, R.string.two_factors_otp_msg, Toast.LENGTH_SHORT).show()
        inputOTP.visibility = View.VISIBLE
    }

    override fun startMainActivity() {
        val activity = getBaseActivity() as LoginActivity
        activity.run{
            sendLoginBroadCast()
            startMainActivity()
        }
    }

    fun setBundle(bundle: Bundle) { arguments = bundle }

    private fun toggleInputVisible(isBasic: Boolean) {
        inputPassword.visibility = if (isBasic) {View.VISIBLE} else {View.GONE}
        inputAccessToken.visibility = if (isBasic) {View.GONE} else {View.VISIBLE}
    }

    private fun setOnclickListeners() {
        btnLoginSubmit.setOnClickListener {
            presenter.onLoginSubmitClicked(
                    editTextUsername.text.toString(),
                    if (isBasic) editTextPassword.text.toString() else editTextAccessToken.text.toString(),
                    editTextOTP.text.toString()
            )
        }
    }

}