package xyz.laziness.dailycommit.ui.modules.main.settings.view

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import xyz.laziness.dailycommit.R


class AppSettingPrefCompat : PreferenceFragmentCompat() {

    companion object {

        internal const val TAG = "AppSettingFragment"

        fun getInstance(): AppSettingPrefCompat = AppSettingPrefCompat()

    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.app_setting_pref)
    }

}