package xyz.laziness.dailycommit.ui.modules.main.friends

import dagger.Module
import dagger.Provides
import xyz.laziness.dailycommit.ui.modules.main.friends.interactor.FriendsStatusInteractor
import xyz.laziness.dailycommit.ui.modules.main.friends.interactor.FriendsStatusInteractorImpl
import xyz.laziness.dailycommit.ui.modules.main.friends.presenter.FriendsStatusPresenter
import xyz.laziness.dailycommit.ui.modules.main.friends.presenter.FriendsStatusPresenterImpl
import xyz.laziness.dailycommit.ui.modules.main.friends.view.FriendsStatusView


@Module
class FriendsStatusModule {

    @Provides
    internal fun provideFriendsStatusPresenter(preseter: FriendsStatusPresenterImpl<FriendsStatusView, FriendsStatusInteractor>) :
            FriendsStatusPresenter<FriendsStatusView, FriendsStatusInteractor> = preseter

    @Provides
    internal fun provideFriendsStatusInteractor(interactor: FriendsStatusInteractorImpl) :
            FriendsStatusInteractor = interactor

}