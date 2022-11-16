package com.example.rssapp.management.data

import com.example.rssapp.management.data.xml.XmlLocalDataSource
import com.example.rssapp.management.domain.UserRssRepository

class RssDataRepository (private val source: XmlLocalDataSource): UserRssRepository{

    override fun saveUserRss(url: String, name: String) {
        source.createRss(url, name)
    }


}