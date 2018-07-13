package xyz.laziness.dailycommit.utils.extensions

import android.content.SharedPreferences


inline fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    action(editor)
    editor.apply()
}