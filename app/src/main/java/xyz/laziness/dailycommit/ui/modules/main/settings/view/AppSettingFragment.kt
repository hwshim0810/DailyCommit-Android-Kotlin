package xyz.laziness.dailycommit.ui.modules.main.settings.view

import android.content.Context
import android.os.Bundle
import android.support.v7.preference.ListPreference
import android.support.v7.preference.PreferenceFragmentCompat
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.data.preference.AppPreference
import xyz.laziness.dailycommit.utils.AppConstants
import xyz.laziness.dailycommit.utils.Colors


class AppSettingPrefCompat : PreferenceFragmentCompat() {

    companion object {

        internal const val TAG = "AppSettingFragment"

        fun getInstance(): AppSettingPrefCompat = AppSettingPrefCompat()

    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.sharedPreferencesName = AppConstants.PREF_NAME
        addPreferencesFromResource(R.xml.app_setting_pref)

        setColorPrefSummary(AppPreference.PREF_KEY_BLOCK_COLOR)

        context?.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE).run {
            this?.registerOnSharedPreferenceChangeListener { _, key ->
                setColorPrefSummary(key)
            }
        }
    }

    private fun setColorPrefSummary(key: String) {
        val colorPref = findPreference(key)

        if (colorPref is ListPreference)
            colorPref.apply {
                summary = entry ?: Colors.DEFAULTS
                value = value ?: Colors.DEFAULTS
            }

    }

}