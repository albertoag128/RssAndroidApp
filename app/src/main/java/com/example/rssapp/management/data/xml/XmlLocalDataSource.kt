package com.example.rssapp.management.data.xml

import android.content.SharedPreferences
import com.example.app.serializer.KSerializer
import com.example.rssapp.management.data.RssLocalSource
import com.example.rssapp.management.domain.UserRss

class XmlLocalDataSource (val sharedPreferences: SharedPreferences, private val serializer: KSerializer) : RssLocalSource{

    private val editor = sharedPreferences.edit()

    override fun createRss(url: String, name: String) {
        editor.putString(url, serializer.toJson(UserRss(url, name), UserRss::class.java))
        editor.apply()
    }

    override fun getUserRss(): List<UserRss> {
        val rssList = mutableListOf<UserRss>()
        sharedPreferences.all.forEach {
            editor.apply {
                rssList.add(serializer.fromJson(it.value as String, UserRss::class.java))
            }.apply()
        }
        return rssList
    }

    override fun deleteRss(url: String) {
        editor.remove(url).apply()
    }


}