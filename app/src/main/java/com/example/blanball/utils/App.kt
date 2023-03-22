package com.example.blanball.utils

import android.app.Application
import com.example.blanball.utils.di.*

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .dataModule(DataModule(context = this))
            .dataStoreModule(DataStoreModule(context = this))
            .build()
    }
}