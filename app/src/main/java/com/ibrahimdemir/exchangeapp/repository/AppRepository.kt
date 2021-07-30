package com.ibrahimdemir.exchangeapp.repository

import com.ibrahimdemir.exchangeapp.data.ResultData
import com.ibrahimdemir.exchangeapp.model.CoinDetailResponse
import com.ibrahimdemir.exchangeapp.model.CoinResponse

interface AppRepository {
    suspend fun getAllCoins(): ResultData<CoinResponse>

    suspend fun getCoinDetail(
        cod: String
    ): ResultData<CoinDetailResponse>
}