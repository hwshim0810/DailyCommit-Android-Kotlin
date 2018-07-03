package xyz.laziness.dailycommit.widget

import dagger.Module
import dagger.Provides
import xyz.laziness.dailycommit.data.preference.AppPreference
import xyz.laziness.dailycommit.widget.provider.WidgetDataProvider


@Module
class WidgetModule {

    @Provides
    internal fun provideWigetDataProvider(appPreference: AppPreference): WidgetDataProvider =
            WidgetDataProvider(appPreference)

}