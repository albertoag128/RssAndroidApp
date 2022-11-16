package com.example.rssapp.management.domain

interface UserRssRepository {
    fun saveUserRss(url:String, name:String)
}