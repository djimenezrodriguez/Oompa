package com.napptilus.oompaloompa.dagger

import android.app.Application

class App : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}