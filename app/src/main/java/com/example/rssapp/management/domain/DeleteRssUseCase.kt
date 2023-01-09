package com.example.rssapp.management.domain

class DeleteRssUseCase (val repository: UserRssRepository) {
    suspend fun invoke(url:String){
        repository.deleteRss(url)
    }
}