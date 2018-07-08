package xyz.laziness.dailycommit.ui.modules.login.choice.presenter

import xyz.laziness.dailycommit.ui.base.presenter.BasePresenter
import xyz.laziness.dailycommit.ui.modules.login.choice.interactor.LoginChoiceInteractor
import xyz.laziness.dailycommit.ui.modules.login.choice.view.LoginChoiceView


interface LoginChoicePresenter<V: LoginChoiceView, I: LoginChoiceInteractor> : BasePresenter<V, I> {

    fun onBasicLoginButtonClick(): Unit?

    fun onWebViewLoginButtonClick(): Unit?

}