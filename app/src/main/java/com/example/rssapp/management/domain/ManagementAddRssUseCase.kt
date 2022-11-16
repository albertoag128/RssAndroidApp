package com.example.rssapp.management.domain

class ManagementAddRssUseCase(val repository: ManagementRepository) {
    fun execute(name:String, url:String){
        repository.saveRss(ManagementModel(name, url))
    }
}