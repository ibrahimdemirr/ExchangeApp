package com.ibrahimdemir.exchangeapp.repository

import com.ibrahimdemir.exchangeapp.data.RemoteDataNotFoundException
import com.ibrahimdemir.exchangeapp.data.ResultData
import com.ibrahimdemir.exchangeapp.di.IoDispatcher
import com.ibrahimdemir.exchangeapp.model.CoinDetailResponse
import com.ibrahimdemir.exchangeapp.model.CoinResponse
import com.ibrahimdemir.exchangeapp.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AppRepository {
    override suspend fun getAllCoins(): ResultData<CoinResponse> {
        return when (val result = remoteDataSource.getAllCoins()) {
            is ResultData.Success -> {
                ResultData.Success(result.data)
            }
            is ResultData.Error -> {
                ResultData.Error(RemoteDataNotFoundException())
            }
        }
    }

    override suspend fun getCoinDetail(
        cod: String
    ): ResultData<CoinDetailResponse> {
        return when (val result = remoteDataSource.getCoinDetail(cod)) {
            is ResultData.Success -> {
                ResultData.Success(result.data)
            }
            is ResultData.Error -> {
                ResultData.Error(RemoteDataNotFoundException())
            }
        }
    }
}