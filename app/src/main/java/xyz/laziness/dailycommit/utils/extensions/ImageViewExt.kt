package xyz.laziness.dailycommit.utils.extensions

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import xyz.laziness.dailycommit.R


internal fun ImageView.loadImage(url: String?, @DrawableRes loadingImageRes: Int = R.drawable.ic_dashboard_black_24dp) {
    Glide.with(this.context)
            .load(url)
            .centerCrop()
            .placeholder(loadingImageRes)
            .into(this)
}