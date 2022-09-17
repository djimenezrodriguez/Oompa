package com.napptilus.oompaloompa.dagger

import android.app.Application
import android.appwidget.AppWidgetProviderInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {
    @Provides
    @Singleton
    fun provideApp() = app
}