package com.example.rssapp.management.presentation

import android.content.Context
import com.example.app.serializer.GsonSerializer
import com.example.rssapp.management.data.RssDataRepository
import com.example.rssapp.management.data.datastore.RssDSLocalDataSource
import com.example.rssapp.management.domain.AddUserRssUseCase
import com.example.rssapp.management.domain.DeleteRssUseCase
import com.example.rssapp.management.domain.GetUserRssUseCase

class DataStoreFactory {

    fun injectViewModel(context: Context): RssDataStoreViewModel {
        val repository = injectRepository(context)
        return RssDataStoreViewModel(
            GetUserRssUseCase(repository),
            AddUserRssUseCase(repository),
            DeleteRssUseCase(repository)
        )
    }

    private fun injectRepository(context: Context) =
        RssDataRepository(RssDSLocalDataSource(context, GsonSerializer()))

}