package com.udacity.asteroidradar.utils

import android.content.Context
import android.preference.PreferenceManager


class Utils(private val context: Context) {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)


    var hasCatcheData: Boolean
        get() = preferences.getBoolean(IS_CATCHE_HAS_DATA_KEY, false)
        set(value) {
            preferences.edit().putBoolean(IS_CATCHE_HAS_DATA_KEY, value).apply()
        }

    companion object {
        const val IS_CATCHE_HAS_DATA_KEY = "is_cache_has_data_key"
    }
}