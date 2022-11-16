package com.example.rssapp.management.domain

class AddRssUseCase(private val repository: UserRssRepository) {

    fun execute(name:String, url:String){
        repository.saveUserRss(url, name)
    }

    data class SaveUserRss(val url:String, val name:String)
}