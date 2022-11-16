package com.example.rssapp.management.domain

class ManagementAddRssUseCase(private val repository: ManagementRepository) {
    suspend fun execute(url:String, name:String){
        repository.saveRss(url, name)
    }
}