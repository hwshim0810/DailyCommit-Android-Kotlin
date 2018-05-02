package xyz.laziness.dailycommit.ui.modules.login.choice

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xyz.laziness.dailycommit.ui.modules.login.choice.view.LoginChoiceFragment


@Module
internal abstract class LoginChoiceProvider {

    @ContributesAndroidInjector(modules = [LoginChoiceModule::class])
    internal abstract fun provideLoginChoiceFragmentFactory(): LoginChoiceFragment

}