package com.ibrahimdemir.exchangeapp.di.modules

import com.ibrahimdemir.exchangeapp.api.ApiService
import com.ibrahimdemir.exchangeapp.di.IoDispatcher
import com.ibrahimdemir.exchangeapp.remote.RemoteDataSourceImpl
import com.ibrahimdemir.exchangeapp.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAppRepository(
        api: ApiService,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): AppRepositoryImpl {
        val userDataSource = RemoteDataSourceImpl(api, ioDispatcher)
        return AppRepositoryImpl(userDataSource, ioDispatcher)
    }
}