package com.example.rssapp.management.presentation

import android.content.SharedPreferences
import com.example.app.serializer.KSerializer
import com.example.rssapp.management.data.RssDataRepository
import com.example.rssapp.management.data.xml.XmlLocalDataSource
import com.example.rssapp.management.domain.DeleteRssUseCase
import com.example.rssapp.management.domain.GetUserRssUseCase

class RssManagerFactory {

    fun getRss(serializer:KSerializer, sharedPreferences: SharedPreferences):RssManagerViewModel{
        return RssManagerViewModel(
            GetUserRssUseCase(
                RssDataRepository(
                    XmlLocalDataSource(
                        sharedPreferences, serializer
                    )
                )
            ),
            DeleteRssUseCase(
                RssDataRepository(
                    XmlLocalDataSource(
                        sharedPreferences, serializer
                    )
                )
            )
        )
    }

}