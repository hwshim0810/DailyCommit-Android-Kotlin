package xyz.laziness.dailycommit.ui.modules.login.view

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.ui.base.view.BaseActivity
import xyz.laziness.dailycommit.ui.modules.login.choice.view.LoginChoiceFragment
import xyz.laziness.dailycommit.ui.modules.login.input.view.LoginInputFragment
import xyz.laziness.dailycommit.ui.modules.login.interactor.LoginInteractor
import xyz.laziness.dailycommit.ui.modules.login.presenter.LoginPresenter
import xyz.laziness.dailycommit.ui.modules.main.view.MainActivity
import xyz.laziness.dailycommit.utils.AppConstants
import xyz.laziness.dailycommit.utils.BundleConstants
import xyz.laziness.dailycommit.utils.extensions.replaceFragmentInActivity
import xyz.laziness.dailycommit.utils.wraaper.Bundler
import xyz.laziness.dailycommit.widget.receiver.WidgetReceiver
import javax.inject.Inject


class LoginActivity : BaseActivity(), LoginView, HasSupportFragmentInjector {

    @Inject
    internal lateinit var presenter: LoginPresenter<LoginView, LoginInteractor>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private val fragmentFrame: Fragment by lazy { supportFragmentManager.findFragmentById(R.id.contentFrame) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun openLoginChooseFragment() {
        fragmentFrame as LoginChoiceFragment? ?: LoginChoiceFragment.getInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame, false)
        }
    }

    override fun openBasicLoginFragment() {
        fragmentFrame as LoginInputFragment? ?: LoginInputFragment.getInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
            it.setBundle(Bundler().put(BundleConstants.INPUT_DISPLAY, true))
        }
    }

    override fun openTokenLoginFragment() {
        fragmentFrame as LoginInputFragment? ?: LoginInputFragment.getInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
            it.setBundle(Bundler().put(BundleConstants.INPUT_DISPLAY, false))
        }
    }

    override fun openLoginInputFragment(loginMethod: AppConstants.LoginMethod) {
        when (loginMethod) {
            AppConstants.LoginMethod.BASIC -> openBasicLoginFragment()
            AppConstants.LoginMethod.TOKEN -> openTokenLoginFragment()
            else -> return
        }
    }

    override fun startMainActivity() = Intent(this, MainActivity::class.java).run {
        startActivity(this)
        finish()
    }

    override fun onViewBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) super.superOnBackPressed()
        else presenter.onBackPressed()
    }

    override fun sendLoginBroadCast() {
        val intent = Intent(this, WidgetReceiver::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        }
        sendBroadcast(intent)
    }

    override fun showLoginError(errCode: Int) {
        when (errCode) {
            AppConstants.EMPTY_USERNAME_ERROR -> Toast.makeText(this, getString(R.string.empty_username_msg), Toast.LENGTH_SHORT).show()
            AppConstants.LOGIN_FAILURE_ERROR -> Toast.makeText(this, getString(R.string.login_failure_msg), Toast.LENGTH_SHORT).show()
            AppConstants.EMPTY_SECRET_ERROR -> Toast.makeText(this, getString(R.string.empty_token_msg), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        presenter.onHandleOauthIntent(intent)
        setIntent(null)
    }

    override fun onResume() {
        super.onResume()
        presenter.onHandleOauthIntent(intent)
        intent = null
    }

}
