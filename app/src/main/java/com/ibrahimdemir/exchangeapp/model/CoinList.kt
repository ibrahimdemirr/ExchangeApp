package com.ibrahimdemir.exchangeapp.model

import com.google.gson.annotations.SerializedName

data class CoinList(
    @SerializedName("def") val def: String?,
    @SerializedName("hig") val hig: String?,
    @SerializedName("clo") val clo: String?,
    @SerializedName("buy") val buy: String?,
    @SerializedName("ddi") val ddi: String?,
    @SerializedName("cl3") val cl3: String?,
    @SerializedName("pdc") val pdc: String?,
    @SerializedName("tke") val tke: String?,
    @SerializedName("rtp") val rtp: Boolean?,
    @SerializedName("pdd") val pdd: String?,
    @SerializedName("low") val low: String?,
    @SerializedName("cod") val cod: String?,
    @SerializedName("sel") val sel: String?,
    @SerializedName("las") val las: String?
)
