package com.example.rssapp.management.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.app.serializer.GsonSerializer
import com.example.rssapp.management.domain.UserRss
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "user_rss_datastore"
)

class RssDSLocalDataSource (private val context: Context, private val serializer: GsonSerializer){

    suspend fun saveUserRss(url: String, name: String) {
        context.dataStore.edit { preferences ->
                preferences[stringPreferencesKey(url)] = serializer.toJson(UserRss(url, name), UserRss::class.java)
        }
    }

    fun obtainUserRss(): Flow<List<UserRss>> {
        return context.dataStore.data
            .map {
                it.asMap().values.map { jsonString ->
                    serializer.fromJson(jsonString as String, UserRss::class.java)
                }
            }
    }

    suspend fun deleteUserRss(url: String){
        context.dataStore.edit {
            it.remove(stringPreferencesKey(url))
        }
    }

}