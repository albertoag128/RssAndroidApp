package com.example.rssapp.management.presentation

import android.content.Context
import android.content.SharedPreferences
import com.example.app.serializer.GsonSerializer
import com.example.app.serializer.KSerializer
import com.example.rssapp.management.data.RssDataRepository
import com.example.rssapp.management.data.datastore.RssDSLocalDataSource
import com.example.rssapp.management.data.xml.XmlLocalDataSource
import com.example.rssapp.management.domain.DeleteRssUseCase
import com.example.rssapp.management.domain.GetUserRssUseCase

class RssManagerFactory {

    fun getRss(serializer:KSerializer, applicationContext: Context):RssManagerViewModel{
        return RssManagerViewModel(
            GetUserRssUseCase(
                RssDataRepository(
                    RssDSLocalDataSource(
                        applicationContext, GsonSerializer()
                    )
                )
            ),
            DeleteRssUseCase(
                RssDataRepository(
                    RssDSLocalDataSource(
                        applicationContext, GsonSerializer()

                    )
                )
            )
        )
    }
}