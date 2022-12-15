package com.example.rssapp.showfeed.domain

data class NewsRss(val channel:String, val title:String, val description:String, val link:String, val pubDate:String, val creator:String, val content:String, val thumbnail:String)