package com.example.rssapp.showfeed.data.remote.api

import com.example.rssapp.showfeed.domain.NewsRss
import com.example.rssapp.showfeed.domain.RssNewsFeedUseCaseModel

fun NewsRss.toDomain(): RssNewsFeedUseCaseModel {
    return RssNewsFeedUseCaseModel(this.thumbnail, this.title, this.description, this.link, this.pubDate)
}