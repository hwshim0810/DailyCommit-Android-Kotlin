package xyz.laziness.dailycommit.widget.receiver

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.widget.RemoteViews
import com.bumptech.glide.request.target.AppWidgetTarget
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.R.id.myContributionsWidgetView
import xyz.laziness.dailycommit.ui.modules.login.view.LoginActivity
import xyz.laziness.dailycommit.utils.image.ImageHelper
import xyz.laziness.dailycommit.widget.provider.WidgetDataProvider
import javax.inject.Inject


class WidgetReceiver : AppWidgetProvider() {

    @Inject
    internal lateinit var dataProvider: WidgetDataProvider

    override fun onReceive(context: Context?, intent: Intent?) {
        AndroidInjection.inject(this, context)

        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE == intent?.action) {
            val appWidgetIds = AppWidgetManager.getInstance(context)
                    .getAppWidgetIds(ComponentName(context, this::class.java))

            if (appWidgetIds.isNotEmpty())
                this.onUpdate(context, AppWidgetManager.getInstance(context), appWidgetIds)
        }

        super.onReceive(context, intent)
    }

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        if (context == null || appWidgetIds == null) return

        if (dataProvider.isLoggedIn()) {
            // LogIn status
            val remoteView = RemoteViews(context.packageName, R.layout.widget_contribution)
            val imageView = AppWidgetTarget(context, remoteView, R.id.myContributionsWidgetView, *appWidgetIds)

            Observable.create(ObservableOnSubscribe<Void> {
                dataProvider.getMyContributionSVGBuilder(context)
                        .override(220, 72)
                        .placeholder(R.drawable.img_preview_graph)
                        .error(R.drawable.ic_baseline_error_outline_24px)
                        .into(imageView)

                remoteView.run {
                    val pendingIntent = getGraphUpdateIntent(context)
                    setOnClickPendingIntent(myContributionsWidgetView, pendingIntent)
                }
                appWidgetManager?.updateAppWidget(appWidgetIds, remoteView)
            }).subscribe({}, {})
        } else {
            // Logout status
            val remoteView = RemoteViews(context.packageName, R.layout.widget_require_login)
            val backgroundBitmap = ImageHelper.drawableToBitmap(ContextCompat.getDrawable(context, R.drawable.img_preview_graph))

            remoteView.run {
                val pendingIntent = getLoginActivityIntent(context)
                setImageViewBitmap(R.id.imageViewWidgetLogin, ImageHelper.blur(context, backgroundBitmap, 25.0f))
                setOnClickPendingIntent(R.id.layoutWidgetLogin, pendingIntent)
            }
            AppWidgetManager.getInstance(context).updateAppWidget(ComponentName(context, this::class.java), remoteView)
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    private fun getLoginActivityIntent(context: Context): PendingIntent {
        return PendingIntent.getActivity(context, 0,
                Intent(context, LoginActivity::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun getGraphUpdateIntent(context: Context): PendingIntent {
        val intent = Intent(context, WidgetReceiver::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        }
        return PendingIntent.getBroadcast(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

}
