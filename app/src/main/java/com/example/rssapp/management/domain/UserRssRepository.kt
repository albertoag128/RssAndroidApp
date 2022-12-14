package com.example.rssapp.management.domain

import kotlinx.coroutines.flow.Flow

interface UserRssRepository {
    suspend fun saveUserRss(url:String, name:String)
    fun getUserRss(): Flow<List<UserRss>>
    suspend fun deleteRss(url:String)
}