package com.example.rssapp.management.presentation

import android.content.Context
import android.content.SharedPreferences
import com.example.app.serializer.GsonSerializer
import com.example.app.serializer.KSerializer
import com.example.rssapp.management.data.RssDataRepository
import com.example.rssapp.management.data.datastore.RssDSLocalDataSource
import com.example.rssapp.management.data.xml.XmlLocalDataSource
import com.example.rssapp.management.domain.AddUserRssUseCase

class RssFormFactory {


    fun saveUserRss(sharedPreferences: SharedPreferences, applicationContext:Context, serializer:KSerializer):RssFormViewModel{
        return RssFormViewModel(
            AddUserRssUseCase(
                RssDataRepository(
                    RssDSLocalDataSource(
                        applicationContext, GsonSerializer()
                    )
                )
            )
        )
    }



}