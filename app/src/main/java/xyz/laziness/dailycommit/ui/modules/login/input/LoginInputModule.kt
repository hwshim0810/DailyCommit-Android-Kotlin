package xyz.laziness.dailycommit.ui.modules.login.input

import dagger.Module
import dagger.Provides
import xyz.laziness.dailycommit.ui.modules.login.input.interactor.LoginInputInteractor
import xyz.laziness.dailycommit.ui.modules.login.input.interactor.LoginInputInteractorImpl
import xyz.laziness.dailycommit.ui.modules.login.input.presenter.LoginInputPresenter
import xyz.laziness.dailycommit.ui.modules.login.input.presenter.LoginInputPresenterImpl
import xyz.laziness.dailycommit.ui.modules.login.input.view.LoginInputView


@Module
class LoginInputModule {

    @Provides
    internal fun provideLoginInputPresenter(presenter: LoginInputPresenterImpl<LoginInputView, LoginInputInteractor>) :
            LoginInputPresenter<LoginInputView, LoginInputInteractor> = presenter

    @Provides
    internal fun provideLoginInputInteractor(interactor: LoginInputInteractorImpl) :
            LoginInputInteractor = interactor

}