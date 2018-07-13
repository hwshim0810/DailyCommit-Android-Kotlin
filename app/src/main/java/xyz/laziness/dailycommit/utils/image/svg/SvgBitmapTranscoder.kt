package xyz.laziness.dailycommit.utils.image.svg

import android.graphics.*
import android.util.Log
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder
import com.caverock.androidsvg.SVG


class SvgBitmapTranscoder : ResourceTranscoder<SVG, Bitmap> {

    override fun getId(): String = ""

    override fun transcode(toTranscode: Resource<SVG>?): Resource<Bitmap> {
        val svg = toTranscode?.get()
        val picture = svg?.renderToPicture()

        val picWidth = picture?.width ?: 0
        val picHeight = picture?.height ?: 0
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = Color.TRANSPARENT
        }
        val bitmap: Bitmap = Bitmap.createBitmap(picWidth, picHeight, Bitmap.Config.ARGB_8888)

        Canvas(bitmap).run {
            drawPaint(paint)
            drawPicture(picture)
        }

        return SimpleResource<Bitmap>(bitmap)
    }
}