package xyz.laziness.dailycommit.ui.modules.main.friends

import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import xyz.laziness.dailycommit.ui.modules.main.friends.interactor.FriendsStatusInteractor
import xyz.laziness.dailycommit.ui.modules.main.friends.interactor.FriendsStatusInteractorImpl
import xyz.laziness.dailycommit.ui.modules.main.friends.presenter.FriendsStatusPresenter
import xyz.laziness.dailycommit.ui.modules.main.friends.presenter.FriendsStatusPresenterImpl
import xyz.laziness.dailycommit.ui.modules.main.friends.view.FriendStatusAdapter
import xyz.laziness.dailycommit.ui.modules.main.friends.view.FriendsStatusFragment
import xyz.laziness.dailycommit.ui.modules.main.friends.view.FriendsStatusView


@Module
class FriendsStatusModule {

    @Provides
    internal fun provideFriendsStatusPresenter(preseter: FriendsStatusPresenterImpl<FriendsStatusView, FriendsStatusInteractor>) :
            FriendsStatusPresenter<FriendsStatusView, FriendsStatusInteractor> = preseter

    @Provides
    internal fun provideFriendsStatusInteractor(interactor: FriendsStatusInteractorImpl) :
            FriendsStatusInteractor = interactor

    @Provides
    internal fun provideFriendsStatusAdapter(): FriendStatusAdapter = FriendStatusAdapter(ArrayList())

    @Provides
    internal fun provideLinearLayoutManager(fragment: FriendsStatusFragment): LinearLayoutManager = LinearLayoutManager(fragment.activity)

}