package xyz.laziness.dailycommit.ui.modules.login.choice.presenter

import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenterImpl
import xyz.laziness.dailycommit.ui.modules.login.choice.interactor.LoginChoiceInteractor
import xyz.laziness.dailycommit.ui.modules.login.choice.view.LoginChoiceView
import xyz.laziness.dailycommit.utils.AppConstants
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import javax.inject.Inject


class LoginChoicePresenterImpl<V: LoginChoiceView, I: LoginChoiceInteractor>
@Inject internal constructor(interactor: I,
                             schedulerHelper: SchedulerHelper,
                             disposable: CompositeDisposable) :
        BasePresenterImpl<V, I>(interactor = interactor, schedulerHelper = schedulerHelper, compositeDisposable = disposable),
        LoginChoicePresenter<V, I> {


    override fun onBasicLoginButtonClick() = getView()?.openLoginInputFragment(AppConstants.LoginMethod.BASIC)

    override fun onWebViewLoginButtonClick() {
        interactor?.let {
            compositeDisposable.add(
                    it.getCustomTabsIntent()
                            .compose(schedulerHelper.ioToMainSingleScheduler())
                            .subscribe({
                                getView()?.openGithubLoginWebView(it)
                            }, {
                                this.onError()
                            })
            )
        }
    }
}