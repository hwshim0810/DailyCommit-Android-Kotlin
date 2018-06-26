package xyz.laziness.dailycommit.data.preference

import xyz.laziness.dailycommit.utils.AppConstants


interface BasePreference {

    fun getCurrentLoginState(): Int

    fun setCurrentLoginState(type: AppConstants.LoginState)

    fun getCurrentLoginMethod(): Int

    fun setCurrentLoginMethod(loginMethod: AppConstants.LoginMethod)

    fun setCurrentUserToken(userToken: String?)

    fun getCurrentUserToken(): String?

    fun setCurrentUserName(userName: String)

    fun getCurrentUserName(): String

}