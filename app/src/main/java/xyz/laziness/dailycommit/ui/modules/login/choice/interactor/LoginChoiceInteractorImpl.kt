package xyz.laziness.dailycommit.ui.modules.login.choice.interactor

import android.content.Context
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import io.reactivex.Single
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractorImpl
import javax.inject.Inject


class LoginChoiceInteractorImpl
@Inject constructor(private val context: Context) : BaseInteractorImpl(), LoginChoiceInteractor {


    override fun getCustomTabsIntent(): Single<CustomTabsIntent> =
            Single.fromCallable {
                CustomTabsIntent.Builder()
                        .addDefaultShareMenuItem()
                        .setToolbarColor(ContextCompat.getColor(context, R.color.level_three))
                        .setShowTitle(true)
                        .build()
            }
}