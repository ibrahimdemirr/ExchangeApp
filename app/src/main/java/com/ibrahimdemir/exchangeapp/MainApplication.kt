package com.ibrahimdemir.exchangeapp

import android.app.Application
import com.ibrahimdemir.exchangeapp.di.component.AppComponents
import com.ibrahimdemir.exchangeapp.di.component.DaggerAppComponents
import com.ibrahimdemir.exchangeapp.di.modules.AppModule

open class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponents = initDagger(this)
    }

    private fun initDagger(app: MainApplication): AppComponents =
        DaggerAppComponents.builder()
            .appModule(AppModule(app))
            .build()

    companion object {
        lateinit var appComponents: AppComponents
    }
}