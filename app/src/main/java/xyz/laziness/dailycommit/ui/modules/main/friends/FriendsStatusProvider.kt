package xyz.laziness.dailycommit.ui.modules.main.friends

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xyz.laziness.dailycommit.ui.modules.main.friends.view.FriendsStatusFragment


@Module
internal abstract class FriendsStatusProvider {

    @ContributesAndroidInjector(modules = [FriendsStatusModule::class])
    internal abstract fun provideFriendsStatusFragmentFactory(): FriendsStatusFragment

}