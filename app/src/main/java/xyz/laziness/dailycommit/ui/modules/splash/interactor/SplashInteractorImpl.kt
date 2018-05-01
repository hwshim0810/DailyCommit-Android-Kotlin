package xyz.laziness.dailycommit.ui.modules.splash.interactor

import android.content.Context
import xyz.laziness.dailycommit.data.preference.BasePreference
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractorImpl
import javax.inject.Inject


class SplashInteractorImpl
    @Inject constructor(private val context: Context,
                        appPreference: BasePreference) : BaseInteractorImpl(), SplashInteractor