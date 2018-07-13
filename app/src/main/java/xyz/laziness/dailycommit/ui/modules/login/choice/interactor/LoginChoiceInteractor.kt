package xyz.laziness.dailycommit.ui.modules.login.choice.interactor

import android.support.customtabs.CustomTabsIntent
import io.reactivex.Single
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractor


interface LoginChoiceInteractor : BaseInteractor {

    fun getCustomTabsIntent(): Single<CustomTabsIntent>

}