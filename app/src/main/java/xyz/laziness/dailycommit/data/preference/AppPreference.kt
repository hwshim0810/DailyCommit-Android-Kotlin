package xyz.laziness.dailycommit.data.preference

import android.content.Context
import android.content.SharedPreferences
import xyz.laziness.dailycommit.di.qualifiers.PreferenceInfo
import xyz.laziness.dailycommit.utils.AppConstants
import xyz.laziness.dailycommit.utils.extensions.edit
import javax.inject.Inject


class AppPreference
    @Inject constructor(context: Context, @PreferenceInfo private val prefFileName: String) : BasePreference {

    companion object {
        private const val PREF_KEY_LOGIN_STATE = "PREF_KEY_LOGIN_STATE"
        private const val PREF_KEY_LOGIN_METHOD = "PREF_KEY_LOGIN_METHOD"
        private const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        private const val PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun getCurrentLoginState(): Int = prefs.getInt(PREF_KEY_LOGIN_STATE, AppConstants.LoginState.LOGOUT.state)

    override fun setCurrentLoginState(type: AppConstants.LoginState) = prefs.edit { putInt(PREF_KEY_LOGIN_STATE, type.state) }

    override fun getCurrentLoginMethod(): Int = prefs.getInt(PREF_KEY_LOGIN_METHOD, AppConstants.LoginMethod.BASIC.state)

    override fun setCurrentLoginMethod(loginMethod: AppConstants.LoginMethod) = prefs.edit { putInt(PREF_KEY_LOGIN_METHOD, loginMethod.state) }

    override fun getCurrentUserToken(): String? = prefs.getString(PREF_KEY_ACCESS_TOKEN, "")

    override fun setCurrentUserToken(userToken: String?) = prefs.edit { putString(PREF_KEY_ACCESS_TOKEN, userToken) }

    override fun getCurrentUserName(): String = prefs.getString(PREF_KEY_USER_NAME, "")

    override fun setCurrentUserName(userName: String) = prefs.edit { putString(PREF_KEY_USER_NAME, userName) }
}