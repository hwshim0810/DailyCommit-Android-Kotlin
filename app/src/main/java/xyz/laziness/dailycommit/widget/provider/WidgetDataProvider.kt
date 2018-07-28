package xyz.laziness.dailycommit.widget.provider

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.bumptech.glide.GenericRequestBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.StreamEncoder
import com.bumptech.glide.load.resource.file.FileToStreamDecoder
import com.caverock.androidsvg.SVG
import xyz.laziness.dailycommit.data.network.github.GitHubApiConstants
import xyz.laziness.dailycommit.data.preference.AppPreference
import xyz.laziness.dailycommit.utils.AppConstants
import xyz.laziness.dailycommit.utils.image.svg.SvgDecoder
import xyz.laziness.dailycommit.utils.image.svg.SvgBitmapTranscoder
import java.io.InputStream
import javax.inject.Inject


class WidgetDataProvider
@Inject internal constructor(val appPreference: AppPreference) {

    fun isLoggedIn(): Boolean = appPreference.getCurrentLoginState() != AppConstants.LoginState.LOGOUT.state

    fun getMyContributionSVGBuilder(context: Context): GenericRequestBuilder<Uri, InputStream, SVG, Bitmap> =
            getContributionSVGRequestBuilder(context, appPreference.getCurrentUserName())

    private fun getContributionSVGRequestBuilder(context: Context, userName: String): GenericRequestBuilder<Uri, InputStream, SVG, Bitmap> =
            Glide.with(context)
                    .using(Glide.buildStreamModelLoader(Uri::class.java, context), InputStream::class.java)
                    .from(Uri::class.java)
                    .`as`(SVG::class.java)
                    .transcode(SvgBitmapTranscoder(), Bitmap::class.java)
                    .sourceEncoder(StreamEncoder())
                    .cacheDecoder(FileToStreamDecoder<SVG>(SvgDecoder()))
                    .decoder(SvgDecoder())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .load(Uri.parse(GitHubApiConstants.CONTRIBUTION_URL.format(userName)))

}