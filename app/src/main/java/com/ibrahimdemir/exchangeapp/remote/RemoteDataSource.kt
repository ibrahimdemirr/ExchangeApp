package com.ibrahimdemir.exchangeapp.remote

import com.ibrahimdemir.exchangeapp.data.ResultData
import com.ibrahimdemir.exchangeapp.model.CoinDetailResponse
import com.ibrahimdemir.exchangeapp.model.CoinResponse

interface RemoteDataSource {
    suspend fun getAllCoins(): ResultData<CoinResponse>

    suspend fun getCoinDetail(
        cod: String
    ): ResultData<CoinDetailResponse>
}