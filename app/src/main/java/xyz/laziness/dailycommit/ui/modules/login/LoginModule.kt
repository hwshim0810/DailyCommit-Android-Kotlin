package xyz.laziness.dailycommit.ui.modules.login

import dagger.Module
import dagger.Provides
import xyz.laziness.dailycommit.ui.modules.login.interactor.LoginInteractor
import xyz.laziness.dailycommit.ui.modules.login.interactor.LoginInteractorImpl
import xyz.laziness.dailycommit.ui.modules.login.presenter.LoginPresenter
import xyz.laziness.dailycommit.ui.modules.login.presenter.LoginPresenterImpl
import xyz.laziness.dailycommit.ui.modules.login.view.LoginView


@Module
class LoginModule {

    @Provides
    internal fun provideLoginPresenter(presenter: LoginPresenterImpl<LoginView, LoginInteractor>) :
            LoginPresenter<LoginView, LoginInteractor> = presenter

    @Provides
    internal fun provideLoginInteractor(loginInteractor: LoginInteractorImpl):
            LoginInteractor = loginInteractor

}