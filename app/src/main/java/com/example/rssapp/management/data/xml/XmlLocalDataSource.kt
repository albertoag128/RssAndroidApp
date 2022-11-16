package com.example.rssapp.management.data.xml

import android.content.SharedPreferences
import com.example.app.serializer.KSerializer
import com.example.rssapp.management.data.ManagementLocalSource
import com.example.rssapp.management.domain.Management

class XmlLocalDataSource (private val sharedPreferences: SharedPreferences, private val serializer: KSerializer) : ManagementLocalSource{

    private val editor = sharedPreferences.edit()

    override fun createRss(url: String, name: String) {
        editor.putString(url, serializer.toJson(Management(name, url), Management::class.java))
    }

}