package com.ntech.weedwhiz.core.utils

import android.content.SharedPreferences

class DataStorage(sharedPreferences: SharedPreferences) {
    private var pref: SharedPreferences = sharedPreferences
    private var editor: SharedPreferences.Editor = pref.edit()

    companion object {
        const val KEY_USER_NAME = "KEY_USER_NAME"
        const val KEY_USER_ID = "KEY_USER_ID"
    }

    var userName: String
        get() {
            return pref.getString(KEY_USER_NAME, "").orEmpty()
        }
        set(value) {
            editor.putString(KEY_USER_NAME, value)
            editor.apply()
        }

    var userId: String
        get() {
            return pref.getString(KEY_USER_ID, "").orEmpty()
        }
        set(value) {
            editor.putString(KEY_USER_ID, value)
            editor.apply()
        }
}