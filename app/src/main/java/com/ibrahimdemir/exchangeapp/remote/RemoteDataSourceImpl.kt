package com.ibrahimdemir.exchangeapp.remote

import com.ibrahimdemir.exchangeapp.api.ApiService
import com.ibrahimdemir.exchangeapp.data.ResultData
import com.ibrahimdemir.exchangeapp.di.IoDispatcher
import com.ibrahimdemir.exchangeapp.model.CoinDetailResponse
import com.ibrahimdemir.exchangeapp.model.CoinResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
    private val api: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {
    override suspend fun getAllCoins(): ResultData<CoinResponse> = withContext(ioDispatcher) {
        ResultData.Success(api.getAllCoins())
    }

    override suspend fun getCoinDetail(
        cod: String
    ): ResultData<CoinDetailResponse> = withContext(ioDispatcher) {
        val request = api.getCoinDetail(cod)
        ResultData.Success(request)
    }
}