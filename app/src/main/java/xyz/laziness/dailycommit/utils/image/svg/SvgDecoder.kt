package xyz.laziness.dailycommit.utils.image.svg

import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import java.io.IOException
import java.io.InputStream


class SvgDecoder : ResourceDecoder<InputStream, SVG> {

    override fun getId(): String = "SvgDecoder.xyz.laziness.dailycommit.app"

    override fun decode(source: InputStream?, width: Int, height: Int): Resource<SVG> {
        try {
            val svg = SVG.getFromInputStream(source)
            return SimpleResource<SVG>(svg)
        } catch (ex: SVGParseException) {
            throw IOException("Cannot load SVG from stream", ex)
        }
    }

}