package xyz.laziness.dailycommit.utils


object AppConstants {

    enum class LoginState constructor(val state: Int) {
        LOGOUT(0),
        LOGIN_GITHUB(1)
    }
}