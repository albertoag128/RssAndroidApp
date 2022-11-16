package com.example.rssapp.management.data

import com.example.rssapp.management.data.xml.XmlLocalDataSource
import com.example.rssapp.management.domain.Management
import com.example.rssapp.management.domain.ManagementRepository

class ManagementDataRepository (private val source: XmlLocalDataSource): ManagementRepository{

    override suspend fun saveRss(url: String, name: String) {
        source.createRss(url, name)
    }

}