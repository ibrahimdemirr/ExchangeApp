package com.ibrahimdemir.exchangeapp.model

import com.google.gson.annotations.SerializedName

data class CoinDetailResponse(
    @SerializedName("d") val coinDetailList: ArrayList<CoinDetailList>
)