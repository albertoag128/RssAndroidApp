package com.example.rssapp.management.domain

interface ManagementRepository {
    fun saveRss(model: ManagementModel)
}