package xyz.laziness.dailycommit.ui.modules.login.presenter

import android.content.Intent
import android.util.Log
import com.androidnetworking.error.ANError
import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.data.network.github.GitHubApiConstants
import xyz.laziness.dailycommit.data.network.github.response.OauthTokenResponse
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenterImpl
import xyz.laziness.dailycommit.ui.modules.login.interactor.LoginInteractor
import xyz.laziness.dailycommit.ui.modules.login.view.LoginView
import xyz.laziness.dailycommit.utils.AppConstants
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import java.net.HttpURLConnection
import javax.inject.Inject


class LoginPresenterImpl<V: LoginView, I: LoginInteractor>
@Inject internal constructor(interactor: I,
                             schedulerHelper: SchedulerHelper,
                             disposable: CompositeDisposable) :
        BasePresenterImpl<V, I>(interactor = interactor, schedulerHelper = schedulerHelper, compositeDisposable = disposable),
        LoginPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        setLoginMainDisplay()
    }

    override fun setLoginMainDisplay() = getView()?.openLoginChooseFragment()

    override fun onHandleOauthIntent(intent: Intent?) {
        intent?.takeIf { it.data != null }?.run {
            val uri = this.data

            if (uri.toString().startsWith(GitHubApiConstants.LOGIN_REDIRECT_URL)) {
                val code = uri.getQueryParameter("code")
                if (code.isNotEmpty()) doOauthLoginCall(code)
            }
        }
    }

    private fun doOauthLoginCall(code: String) {
        interactor?.let {
            compositeDisposable.add(
                    it.doOauthAccessTokenCall(code)
                            .compose(schedulerHelper.ioToMainSingleScheduler())
                            .subscribe({
                                updateLoginInfoInPreference(it, AppConstants.LoginMethod.WEB)
                                getView()?.startMainActivity()
                            }, {
                                it as ANError
                                Log.d("EE", it.message)
                                if (it.errorCode == HttpURLConnection.HTTP_UNAUTHORIZED)
                                    getView()?.showLoginError(AppConstants.LOGIN_FAILURE_ERROR)
                                else
                                    this.onError()

                            })
            )
        }
    }

    private fun updateLoginInfoInPreference(tokenResponse: OauthTokenResponse, loginMethod: AppConstants.LoginMethod) =
        interactor?.updateLoginInfoInPreference(tokenResponse, loginMethod)
}