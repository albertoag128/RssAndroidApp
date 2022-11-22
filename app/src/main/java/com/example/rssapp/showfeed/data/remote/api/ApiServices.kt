package com.example.rssapp.showfeed.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServices {
    @GET
    suspend fun getRss(@Url urlRss: String): Response<RssApiModel>
}