package xyz.laziness.dailycommit.utils.progress

import android.content.Context
import android.support.v4.widget.CircularProgressDrawable


object ProgressHelper {

    fun getCircularProgress(context: Context?, stroke: Float = 5f, radius: Float = 30f): CircularProgressDrawable? {
        context?.run {
            return CircularProgressDrawable(context).apply {
                strokeWidth = stroke
                centerRadius = radius
            }
        } ?: return null
    }

}