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
    internal  lateinit var presenter: LoginInputPresenter<LoginInputView, LoginInputInteractor>

    private var isBasic: Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_login_input, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
    }

    override fun initUI() {
        isBasic = arguments?.getBoolean(BundleConstants.INPUT_DISPLAY) ?: true

        toggleInputVisible(isBasic)
        setOnclickListeners()
    }

    override fun showLoginError(errCode: Int) {
        when (errCode) {
            AppConstants.EMPTY_USERNAME_ERROR -> Toast.makeText(context, getString(R.string.empty_username_msg), Toast.LENGTH_SHORT).show()
            AppConstants.LOGIN_FAILURE_ERROR -> Toast.makeText(context, getString(R.string.login_failure_msg), Toast.LENGTH_SHORT).show()
            AppConstants.EMPTY_SECRET_ERROR -> Toast.makeText(context,
                    if (isBasic) getString(R.string.empty_password_msg) else getString(R.string.empty_token_msg), Toast.LENGTH_SHORT).show()
        }
    }

    override fun startMainActivity() {
        val activity = getBaseActivity() as LoginActivity
        activity.startMainActivity()
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
                    if (isBasic) editTextPassword.text.toString() else editTextAccessToken.text.toString()
            )
        }
    }

}