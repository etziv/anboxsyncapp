package com.arcane.anboxsync.preferences

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    companion object {
        private const val BOX_TOKEN_KEY = "boxToken"
        private const val CHAT_ID_KEY = "chatId"
    }

    var boxToken: String?
        get() = sharedPreferences.getString(BOX_TOKEN_KEY, null)
        set(value) = sharedPreferences.edit().putString(BOX_TOKEN_KEY, value).apply()

    var chatId: String?
        get() = sharedPreferences.getString(CHAT_ID_KEY, null)
        set(value) = sharedPreferences.edit().putString(CHAT_ID_KEY, value).apply()
}
