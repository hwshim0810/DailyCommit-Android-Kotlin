package xyz.laziness.dailycommit.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xyz.laziness.dailycommit.widget.WidgetModule
import xyz.laziness.dailycommit.widget.receiver.WidgetReceiver


@Module
abstract class WidgetBuilder {

    @ContributesAndroidInjector(modules = [(WidgetModule::class)])
    abstract fun bindAppWidget(): WidgetReceiver

}