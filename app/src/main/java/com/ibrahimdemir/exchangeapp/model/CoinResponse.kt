package com.ibrahimdemir.exchangeapp.model

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @SerializedName("l") val coinList: ArrayList<CoinList>
)
