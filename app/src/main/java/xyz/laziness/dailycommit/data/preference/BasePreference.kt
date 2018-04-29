package xyz.laziness.dailycommit.data.preference

import xyz.laziness.dailycommit.utils.AppConstants


interface BasePreference {

    fun getCurrentLoginState(): Int

    fun setCurrentLoginState(type: AppConstants.LoginState)

    fun setCurrentUserId(userId: String?)

    fun getCurrentUserId(): String?

}