package xyz.laziness.dailycommit.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import xyz.laziness.dailycommit.DailyCommitApplication
import xyz.laziness.dailycommit.di.builder.ActivityBuilder
import xyz.laziness.dailycommit.di.module.AppModule
import javax.inject.Singleton


@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: DailyCommitApplication)
}