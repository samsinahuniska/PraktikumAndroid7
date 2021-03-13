package com.example.praktikumandroid7.ui.jenisbarang

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktikumandroid7.model.Jenisbarang
import com.example.praktikumandroid7.model.JenisbarangData
import com.example.praktikumandroid7.model.JenisbarangResponse
import com.example.praktikumandroid7.network.Api
import kotlinx.coroutines.launch
import retrofit2.Response


class JenisbarangViewModel : ViewModel() {
    private val _response = MutableLiveData<Jenisbarang>()
    val createResponse = MutableLiveData<Response<JenisbarangResponse>>()

    val response: LiveData<Jenisbarang>
        get() = _response

    init {
        setResponse()
    }

    private fun setResponse() {
        viewModelScope.launch {
            try {
                val listResult = Api.retrofitService.getJenisbarang()
                _response.value = listResult
            } catch (e: Exception) {
                _response.value = null
            }
        }
    }

    fun create(jenisbarangData: JenisbarangData) {
        viewModelScope.launch {
            val response = Api.retrofitService.create(jenisbarangData)
            createResponse.value = response
        }
    }
}