package com.example.rssapp.showfeed.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class ApiClient {

    private val apiServices: ApiServices

    init {
        apiServices = getApiClient()
    }

    private fun getApiClient() =
        createRetrofitClient().create(ApiServices::class.java)

    private fun createRetrofitClient() = Retrofit.Builder()
        .baseUrl("https://google.es/")
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build();

    suspend fun getRss(urlRss: String) = apiServices.getRss(urlRss).body()!!

}