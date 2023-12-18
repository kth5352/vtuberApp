package com.example.smartapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.Dispatchers


class VtuberViewModel : ViewModel() { // ViewModel 상속
    private val SERVER_URL = "https://port-0-smartapp-5r422alq9fmtyp.sel4.cloudtype.app/"
    private val vtuberApi: VtuberApi
    private val _vtuberList = MutableLiveData<List<Vtuber>>()
    val vtuberList: LiveData<List<Vtuber>> // Correcting property name

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        vtuberApi = retrofit.create(VtuberApi::class.java)
        vtuberList = _vtuberList // Assigning LiveData

        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) { // Using IO dispatcher
            try {
                val response = vtuberApi.getVtubers()
                _vtuberList.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace() // Handling error appropriately
            }
        }
    }
}