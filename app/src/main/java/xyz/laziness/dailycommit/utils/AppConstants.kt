package xyz.laziness.dailycommit.utils

import java.util.regex.Pattern


object AppConstants {

    enum class LoginState constructor(val state: Int) {
        LOGOUT(0),
        LOGIN_GITHUB(1)
    }

    enum class LoginMethod constructor(val state: Int) {
        BASIC(0),
        TOKEN(1),
        WEB(2)
    }

    internal const val PREF_NAME = "daily_pref"

    /**
     * Message Codes
     */
    internal const val EMPTY_USERNAME_ERROR = 10
    internal const val INVALID_EMAIL_ERROR = 11
    internal const val EMPTY_SECRET_ERROR = 12
    internal const val LOGIN_FAILURE_ERROR = 13

    internal const val BACK_BTN_SUCCESS = 100
    internal const val BACK_BTN_ERROR = 101

    internal val EMAIL_REGEX_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
}