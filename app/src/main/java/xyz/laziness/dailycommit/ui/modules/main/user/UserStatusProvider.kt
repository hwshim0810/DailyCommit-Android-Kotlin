package xyz.laziness.dailycommit.ui.modules.main.user

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xyz.laziness.dailycommit.ui.modules.main.user.view.UserStatusFragment


@Module
internal abstract class UserStatusProvider {

    @ContributesAndroidInjector(modules = [UserStatusModule::class])
    internal abstract fun provideUserStatusFragmentFactory(): UserStatusFragment

}