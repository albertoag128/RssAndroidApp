package com.example.rssapp.management.domain

class AddUserRssUseCase(private val repository: UserRssRepository) {

    suspend fun invoke(url:String, name:String){
        repository.saveUserRss(url, name)
    }

}