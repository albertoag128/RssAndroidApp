package com.example.rssapp.management.domain

class AddUserRssUseCase(private val repository: UserRssRepository) {

    fun execute(url:String, name:String){
        repository.saveUserRss(url, name)
    }

    data class SaveUserRss(val url:String, val name:String)
}