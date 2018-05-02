package xyz.laziness.dailycommit.ui.modules.login.choice.interactor

import android.content.Context
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractorImpl
import javax.inject.Inject


class LoginChoiceInteractorImpl
@Inject constructor(private val context: Context) : BaseInteractorImpl(), LoginChoiceInteractor