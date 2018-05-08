package xyz.laziness.dailycommit.ui.modules.main

import dagger.Module
import dagger.Provides
import xyz.laziness.dailycommit.ui.modules.main.interactor.MainInteractor
import xyz.laziness.dailycommit.ui.modules.main.interactor.MainInteractorImpl
import xyz.laziness.dailycommit.ui.modules.main.presenter.MainPresenter
import xyz.laziness.dailycommit.ui.modules.main.presenter.MainPresenterImpl
import xyz.laziness.dailycommit.ui.modules.main.view.MainView


@Module
class MainModule {

    @Provides
    internal fun provideMainPresenter(presenter: MainPresenterImpl<MainView, MainInteractor>):
            MainPresenter<MainView, MainInteractor> = presenter

    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractorImpl):
            MainInteractor = mainInteractor

}