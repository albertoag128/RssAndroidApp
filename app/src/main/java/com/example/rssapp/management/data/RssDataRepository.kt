package com.example.rssapp.management.data

import com.example.rssapp.management.data.datastore.RssDSLocalDataSource
import com.example.rssapp.management.data.xml.XmlLocalDataSource
import com.example.rssapp.management.domain.UserRss
import com.example.rssapp.management.domain.UserRssRepository
import kotlinx.coroutines.flow.Flow

class RssDataRepository (private val source: RssDSLocalDataSource): UserRssRepository{

    override suspend fun saveUserRss(url: String, name: String) {
        source.saveUserRss(url, name)
    }

    override fun getUserRss(): Flow<List<UserRss>> {
        return source.obtainUserRss()
    }

    override suspend fun deleteRss(url: String) {
        source.deleteUserRss(url)
    }


}