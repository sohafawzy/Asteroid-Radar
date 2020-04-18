package com.udacity.asteroidradar.utils

import android.content.Context
import android.preference.PreferenceManager


class Utils(private val context: Context) {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)


    var lastCacheUpdate: Long
        get() = preferences.getLong(LAST_CACHE_UPDATE_TIMESTAMP_KEY, 0)
        set(value) {
            preferences.edit().putLong(LAST_CACHE_UPDATE_TIMESTAMP_KEY, value).apply()
        }

    companion object {
        const val LAST_CACHE_UPDATE_TIMESTAMP_KEY = "last_cache_update_timestamp_key"
    }
}