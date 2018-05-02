package xyz.laziness.dailycommit.utils.extensions

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity


fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, @IdRes frameId: Int, addStack: Boolean = true) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
        takeIf { addStack }?.apply { addToBackStack(null) }
    }
}

fun AppCompatActivity.addFragmentInActivity(fragment: Fragment, @IdRes frameId: Int) {
    supportFragmentManager.transact {
        add(frameId, fragment)
    }
}

/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}