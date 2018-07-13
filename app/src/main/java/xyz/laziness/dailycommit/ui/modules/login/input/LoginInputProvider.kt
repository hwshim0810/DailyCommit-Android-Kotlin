package xyz.laziness.dailycommit.ui.modules.login.input

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xyz.laziness.dailycommit.ui.modules.login.input.view.LoginInputFragment


@Module
internal abstract class LoginInputProvider {

    @ContributesAndroidInjector(modules = [LoginInputModule::class])
    internal abstract fun provideLoginInputFragmentFactory(): LoginInputFragment

}