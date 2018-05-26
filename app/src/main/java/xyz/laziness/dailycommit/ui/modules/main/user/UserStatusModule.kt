package xyz.laziness.dailycommit.ui.modules.main.user

import dagger.Module
import dagger.Provides
import xyz.laziness.dailycommit.ui.modules.main.user.interactor.UserStatusInteractor
import xyz.laziness.dailycommit.ui.modules.main.user.interactor.UserStatusInteractorImpl
import xyz.laziness.dailycommit.ui.modules.main.user.presenter.UserStatusPresenter
import xyz.laziness.dailycommit.ui.modules.main.user.presenter.UserStatusPresenterImpl
import xyz.laziness.dailycommit.ui.modules.main.user.view.UserStatusView


@Module
class UserStatusModule {

    @Provides
    internal fun provideUserStatusPresenter(presenter: UserStatusPresenterImpl<UserStatusView, UserStatusInteractor>) :
            UserStatusPresenter<UserStatusView, UserStatusInteractor> = presenter

    @Provides
    internal fun provideUserStatusInteractor(interactor: UserStatusInteractorImpl) :
            UserStatusInteractor = interactor
}