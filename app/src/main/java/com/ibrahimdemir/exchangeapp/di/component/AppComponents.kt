package com.ibrahimdemir.exchangeapp.di.component

import android.content.Context
import com.ibrahimdemir.exchangeapp.di.modules.AppModule
import com.ibrahimdemir.exchangeapp.di.modules.CoroutinesModule
import com.ibrahimdemir.exchangeapp.di.modules.NetworkModule
import com.ibrahimdemir.exchangeapp.di.modules.RepositoryModule
import com.ibrahimdemir.exchangeapp.ui.DashboardFragment
import com.ibrahimdemir.exchangeapp.ui.DetailFragment
import com.ibrahimdemir.exchangeapp.ui.FavoritesFragment
import com.ibrahimdemir.exchangeapp.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        CoroutinesModule::class
    ]
)
interface AppComponents {
    fun context(): Context

    fun inject(mainActivity: MainActivity)

    fun inject(dashboardFragment: DashboardFragment)

    fun inject(detailFragment: DetailFragment)

    fun inject(favoritesFragment: FavoritesFragment)
}