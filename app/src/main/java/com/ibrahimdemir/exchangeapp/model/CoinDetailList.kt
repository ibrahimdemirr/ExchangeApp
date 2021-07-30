package com.ibrahimdemir.exchangeapp.model

import com.google.gson.annotations.SerializedName

data class CoinDetailList(
    @SerializedName("desc") val desc: String?,
    @SerializedName("type") val type: Double?,
    @SerializedName("clo") val clo: String?,
    @SerializedName("fields") val fields: Fields?
)

data class Fields(
    @SerializedName("las") val las: String?,
    @SerializedName("buy") val buy: String?,
    @SerializedName("sel") val sel: String?,
    @SerializedName("low") val low: String?,
    @SerializedName("hig") val hig: String?,
    @SerializedName("ddi") val ddi: String?,
    @SerializedName("pdd") val pdd: String?
)
