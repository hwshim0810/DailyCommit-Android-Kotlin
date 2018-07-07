package xyz.laziness.dailycommit.utils.extensions

import android.support.annotation.DrawableRes
import android.support.v4.widget.CircularProgressDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import xyz.laziness.dailycommit.R


internal fun ImageView.loadImage(url: String?, @DrawableRes loadingImageRes: Int = R.drawable.ic_baseline_help_outline_24px) {
    Glide.with(this.context)
            .load(url)
            .centerCrop()
            .error(R.drawable.ic_baseline_error_outline_24px)
            .placeholder(loadingImageRes)
            .into(this)
}

internal fun ImageView.loadImage(url: String?, progress: CircularProgressDrawable) {
    Glide.with(this.context)
            .load(url)
            .centerCrop()
            .error(R.drawable.ic_baseline_error_outline_24px)
            .placeholder(progress)
            .into(this)
}