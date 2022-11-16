package com.example.rssapp.management.domain

interface ManagementRepository {
    suspend fun saveRss(url:String, name:String)
}