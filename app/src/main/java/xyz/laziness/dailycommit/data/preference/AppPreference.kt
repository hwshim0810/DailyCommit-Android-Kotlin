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
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun getCurrentLoginState(): Int = prefs.getInt(PREF_KEY_LOGIN_STATE, AppConstants.LoginState.LOGOUT.state)

    override fun setCurrentLoginState(type: AppConstants.LoginState) {
        prefs.edit { putInt(PREF_KEY_LOGIN_STATE, type.state) }
    }

    override fun setCurrentUserId(userId: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentUserId(): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}