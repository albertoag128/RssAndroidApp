package com.example.rssapp.showfeed.domain

import com.example.rssapp.showfeed.data.remote.api.toDomain

class GetRssNewsFeedUseCase (private val repository: NewsRssRepository){
    fun invoke(url:String):List<RssNewsFeedUseCaseModel>{
        val list = repository.getRssNewsFeed(url)
        return list.map {
            it.toDomain()
        }
    }
}

data class RssNewsFeedUseCaseModel(val thumbnail: String, val title:String, val description:String, val link:String, val pubDate:String)