package xyz.laziness.dailycommit.ui.modules.login.choice

import dagger.Module
import dagger.Provides
import xyz.laziness.dailycommit.ui.modules.login.choice.interactor.LoginChoiceInteractor
import xyz.laziness.dailycommit.ui.modules.login.choice.interactor.LoginChoiceInteractorImpl
import xyz.laziness.dailycommit.ui.modules.login.choice.presenter.LoginChoicePresenter
import xyz.laziness.dailycommit.ui.modules.login.choice.presenter.LoginChoicePresenterImpl
import xyz.laziness.dailycommit.ui.modules.login.choice.view.LoginChoiceView


@Module
class LoginChoiceModule {

    @Provides
    internal fun provideLoginChoicePresenter(presenter: LoginChoicePresenterImpl<LoginChoiceView, LoginChoiceInteractor>) :
            LoginChoicePresenter<LoginChoiceView, LoginChoiceInteractor> = presenter

    @Provides
    internal fun provideLoginChoiceInteractor(interactor: LoginChoiceInteractorImpl) :
            LoginChoiceInteractor = interactor

}