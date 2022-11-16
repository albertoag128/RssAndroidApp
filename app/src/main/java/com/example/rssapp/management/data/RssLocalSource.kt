package com.example.rssapp.management.data

interface RssLocalSource {
    fun createRss(url:String, name:String)
}