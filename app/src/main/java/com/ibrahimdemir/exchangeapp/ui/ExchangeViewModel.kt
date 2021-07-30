package com.ibrahimdemir.exchangeapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimdemir.exchangeapp.data.ResultData
import com.ibrahimdemir.exchangeapp.model.CoinDetailResponse
import com.ibrahimdemir.exchangeapp.model.CoinResponse
import com.ibrahimdemir.exchangeapp.repository.AppRepositoryImpl
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ExchangeViewModel @Inject constructor(
    private val repositoryImpl: AppRepositoryImpl
) : ViewModel() {

    private var _getAllCoinsLiveData = MutableLiveData<CoinResponse>()
    var getAllCoinsLiveData: LiveData<CoinResponse> = _getAllCoinsLiveData

    private var _getCoinDetailLiveData = MutableLiveData<CoinDetailResponse>()
    var getCoinDetailLiveData: LiveData<CoinDetailResponse> = _getCoinDetailLiveData

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _dataLoading = MutableLiveData<Boolean>()
    var dataLoading: LiveData<Boolean> = _dataLoading

    fun getAllCoins() {
        _dataLoading.postValue(true)
        viewModelScope.launch {
            try {
                when (val response = repositoryImpl.getAllCoins()) {
                    is ResultData.Success -> {
                        _dataLoading.postValue(false)
                        _getAllCoinsLiveData.postValue(response.data)
                    }
                    is ResultData.Error -> {
                        _dataLoading.postValue(false)
                        _errorMessage.postValue(response.exception.toString())
                    }
                }
            } catch (e: Exception) {
                _dataLoading.postValue(false)
                _errorMessage.postValue(e.message)
            }
        }
    }

    fun getCoinDetail(cod: String?) {
        _dataLoading.postValue(true)
        viewModelScope.launch {
            try {
                when (val response = repositoryImpl.getCoinDetail(cod ?: "")) {
                    is ResultData.Success -> {
                        _dataLoading.postValue(false)
                        _getCoinDetailLiveData.postValue(response.data)
                    }
                    is ResultData.Error -> {
                        _dataLoading.postValue(false)
                        _errorMessage.postValue(response.exception.toString())
                    }
                }
            } catch (e: Exception) {
                _dataLoading.postValue(false)
                _errorMessage.postValue(e.message)
            }
        }
    }
}