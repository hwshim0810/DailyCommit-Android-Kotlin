package xyz.laziness.dailycommit.ui.modules.login.input.interactor

import android.content.Context
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractorImpl
import xyz.laziness.dailycommit.ui.modules.login.choice.interactor.LoginChoiceInteractor
import javax.inject.Inject


class LoginInputInteractorImpl
@Inject constructor(private val context: Context) : BaseInteractorImpl(), LoginInputInteractor