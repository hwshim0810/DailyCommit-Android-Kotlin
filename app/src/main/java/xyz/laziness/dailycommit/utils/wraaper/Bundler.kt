package xyz.laziness.dailycommit.utils.wraaper

import android.os.Bundle


class Bundler (private val bundle: Bundle = Bundle()) {

    fun getBundle(): Bundle = bundle

    fun put(key: String, value: Boolean) = bundle.apply { putBoolean(key, value) }

    fun put(key: String, value: String) = bundle.apply { putString(key, value) }

    fun put(key: String, value: Int) = bundle.apply { putInt(key, value) }
}