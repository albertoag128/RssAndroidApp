package com.example.rssapp.showfeed.domain

interface NewsRssRepository {
    fun getRssNewsFeed(url:String): List<NewsRss>
}