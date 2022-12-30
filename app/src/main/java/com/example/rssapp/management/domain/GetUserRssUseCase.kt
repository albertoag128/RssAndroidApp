package com.example.rssapp.management.domain

class GetUserRssUseCase (private val repository: UserRssRepository){
    fun execute():List<UserRss>{
        return repository.getUserRss()
    }
}