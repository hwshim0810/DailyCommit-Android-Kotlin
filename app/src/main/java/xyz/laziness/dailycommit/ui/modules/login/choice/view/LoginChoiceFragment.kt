package xyz.laziness.dailycommit.ui.modules.login.choice.view

import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login_choice.*
import saschpe.android.customtabs.CustomTabsHelper
import saschpe.android.customtabs.WebViewFallback
import xyz.laziness.dailycommit.BuildConfig
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.data.network.github.GitHubApiConstants
import xyz.laziness.dailycommit.ui.base.view.BaseFragment
import xyz.laziness.dailycommit.ui.modules.login.choice.interactor.LoginChoiceInteractor
import xyz.laziness.dailycommit.ui.modules.login.choice.presenter.LoginChoicePresenter
import xyz.laziness.dailycommit.ui.modules.login.view.LoginActivity
import xyz.laziness.dailycommit.utils.AppConstants
import javax.inject.Inject


class LoginChoiceFragment : BaseFragment(), LoginChoiceView {

    companion object {

        fun getInstance(): LoginChoiceFragment = LoginChoiceFragment()

    }

    @Inject
    internal lateinit var presenter: LoginChoicePresenter<LoginChoiceView, LoginChoiceInteractor>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login_choice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initUI() {
        btnBasicLogin.setOnClickListener { presenter.onBasicLoginButtonClick() }
        btnBrowserLogin.setOnClickListener { presenter.onWebViewLoginButtonClick() }
    }

    override fun openLoginInputFragment(loginMethod: AppConstants.LoginMethod) {
        val activity = getBaseActivity() as LoginActivity?
        activity?.openLoginInputFragment(loginMethod)
    }

    override fun openGithubLoginWebView(customTabsIntent: CustomTabsIntent) {
        CustomTabsHelper.addKeepAliveExtra(context, customTabsIntent.intent)
        CustomTabsHelper.openCustomTab(context, customTabsIntent,
                getGithubOAuthURI(), WebViewFallback())
    }

    private fun getGithubOAuthURI() =
            Uri.Builder()
                    .scheme("https")
                    .authority("github.com")
                    .appendPath("login")
                    .appendPath("oauth")
                    .appendPath("authorize")
                    .appendQueryParameter("client_id", GitHubApiConstants.CLIENT_ID)
                    .appendQueryParameter("redirect_uri", GitHubApiConstants.LOGIN_REDIRECT_URL)
                    .appendQueryParameter("scope", GitHubApiConstants.REQUST_SCOPE)
                    .appendQueryParameter("state", BuildConfig.APPLICATION_ID)
                    .build()
}