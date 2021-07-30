package com.ibrahimdemir.exchangeapp.api

import com.ibrahimdemir.exchangeapp.model.CoinDetailResponse
import com.ibrahimdemir.exchangeapp.model.CoinResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("list.php")
    suspend fun getAllCoins(): CoinResponse

    @GET("detail.php?cod=")
    suspend fun getCoinDetail(
        @Query("cod") cod: String
    ): CoinDetailResponse
}