package com.ibrahimdemir.exchangeapp.di.modules

import android.app.Application
import android.content.Context
import com.ibrahimdemir.exchangeapp.di.viewmodels.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun providesContext(): Context {
        return application
    }
}