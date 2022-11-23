package com.example.rssapp.management.domain

class DeleteRssUseCase (val repository: UserRssRepository) {
    fun execute(url:String){
        repository.deleteRss(url)
    }
}