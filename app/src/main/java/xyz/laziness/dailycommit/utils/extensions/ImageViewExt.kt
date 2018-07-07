package xyz.laziness.dailycommit.utils.extensions

import android.support.annotation.DrawableRes
import android.support.v4.widget.CircularProgressDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.CropCircleTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import xyz.laziness.dailycommit.R


internal fun ImageView.loadImageBase(url: String?) =
        Glide.with(this.context)
                .load(url)
                .error(R.drawable.ic_baseline_error_outline_24px)

internal fun ImageView.loadImage(url: String?, @DrawableRes loadingImageRes: Int = R.drawable.ic_baseline_help_outline_24px) =
        loadImageBase(url)
            .centerCrop()
            .placeholder(loadingImageRes)
            .into(this)


internal fun ImageView.loadImage(url: String?, progress: CircularProgressDrawable) =
        loadImageBase(url)
                .centerCrop()
                .placeholder(progress)
                .into(this)

internal fun ImageView.loadCircleImage(url: String?, @DrawableRes loadingImageRes: Int = R.drawable.ic_baseline_help_outline_24px) =
        loadImageBase(url)
                .bitmapTransform(CropCircleTransformation(context))
                .placeholder(loadingImageRes)
                .into(this)


internal fun ImageView.loadCircleImage(url: String?, progress: CircularProgressDrawable) =
        loadImageBase(url)
                .bitmapTransform(CropCircleTransformation(context))
                .placeholder(progress)
                .into(this)

internal fun ImageView.loadRoundedCornerImage(url: String?, @DrawableRes loadingImageRes: Int = R.drawable.ic_baseline_help_outline_24px, radius: Int = 20) =
        loadImageBase(url)
                .bitmapTransform(RoundedCornersTransformation(context, radius, 0))
                .placeholder(loadingImageRes)
                .into(this)


internal fun ImageView.loadRoundedCornerImage(url: String?, progress: CircularProgressDrawable, radius: Int = 20) =
        loadImageBase(url)
                .bitmapTransform(RoundedCornersTransformation(context, radius, 0))
                .placeholder(progress)
                .into(this)