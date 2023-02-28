package com.example.blanball.utils

import android.app.Application
import com.example.blanball.utils.di.AppComponent
import com.example.blanball.utils.di.AppModule
import com.example.blanball.utils.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }
}