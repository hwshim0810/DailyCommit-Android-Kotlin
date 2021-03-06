package xyz.laziness.dailycommit.ui.modules.splash.view

import android.content.Intent
import android.os.Bundle
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.ui.base.view.BaseActivity
import xyz.laziness.dailycommit.ui.modules.login.view.LoginActivity
import xyz.laziness.dailycommit.ui.modules.main.view.MainActivity
import xyz.laziness.dailycommit.ui.modules.splash.interactor.SplashInteractor
import xyz.laziness.dailycommit.ui.modules.splash.presenter.SplashPresenter
import javax.inject.Inject


class SplashActivity : BaseActivity(), SplashView {

    @Inject
    internal lateinit var presenter: SplashPresenter<SplashView, SplashInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onViewBackPressed() = presenter.onBackPressed()

    override fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun startLoginActivity() = Intent(this, LoginActivity::class.java).run {
        startActivity(this)
        finish()
    }
}