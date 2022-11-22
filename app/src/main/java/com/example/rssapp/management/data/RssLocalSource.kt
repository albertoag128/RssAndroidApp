package com.example.rssapp.management.data

import com.example.rssapp.management.domain.UserRss

interface RssLocalSource {
    fun createRss(url:String, name:String)
    fun getUserRss():List<UserRss>
}