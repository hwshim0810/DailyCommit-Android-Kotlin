package xyz.laziness.dailycommit.utils.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur

class ImageHelper {

    companion object {

        fun blur(context: Context, sourceBitmap: Bitmap, radius: Float): Bitmap {
            val bitmap = sourceBitmap.copy(sourceBitmap.config, true)
            val rs = RenderScript.create(context)
            val input = Allocation.createFromBitmap(rs, sourceBitmap,
                    Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT)
            val output = Allocation.createTyped(rs, input.type)

            ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)).run {
                setRadius(radius)   // 0.0f < r <= 25.0f
                setInput(input)
                forEach(output)
                output.copyTo(bitmap)
            }
            return bitmap
        }

        fun drawableToBitmap(drawable: Drawable?, width: Int = 50, height: Int = 50): Bitmap {
            if (drawable is BitmapDrawable)
                return drawable.bitmap

            val w = drawable?.intrinsicWidth ?: 0
            val h = drawable?.intrinsicHeight ?: 0

            val bitmap = Bitmap.createBitmap(if (w > 0) w else width, if (h > 0) h else height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable?.setBounds(0, 0, canvas.width, canvas.height)
            drawable?.draw(canvas)

            return bitmap
        }

    }

}