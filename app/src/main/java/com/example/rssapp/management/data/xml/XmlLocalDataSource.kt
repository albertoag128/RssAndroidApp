package com.example.rssapp.management.data.xml

import android.content.SharedPreferences
import com.example.app.serializer.KSerializer
import com.example.rssapp.management.data.RssLocalSource
import com.example.rssapp.management.domain.UserRss

class XmlLocalDataSource (sharedPreferences: SharedPreferences, private val serializer: KSerializer) : RssLocalSource{

    private val editor = sharedPreferences.edit()

    override fun createRss(url: String, name: String) {
        editor.putString(url, serializer.toJson(UserRss(name, url), UserRss::class.java))
        editor.apply()
    }

}