package com.example.rssapp.management.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rssapp.management.domain.DeleteRssUseCase
import com.example.rssapp.management.domain.GetUserRssUseCase
import com.example.rssapp.management.domain.UserRss
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RssManagerViewModel (private val getUserRssUseCase: GetUserRssUseCase, val deleteRssUseCase: DeleteRssUseCase) : ViewModel(){

    val rssManagerFeedPublisher: MutableLiveData<RssManagerFeedUiState> by lazy {
        MutableLiveData<RssManagerFeedUiState>()
    }

    fun getRss(){
        rssManagerFeedPublisher.value = RssManagerFeedUiState(true)

        viewModelScope.launch(Dispatchers.IO){
            val rssFeed = getUserRssUseCase.execute()
            rssManagerFeedPublisher.postValue(
                RssManagerFeedUiState(
                    isLoading = false,
                    rssFeed = rssFeed

import com.example.rssapp.management.domain.AddUserRssUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RssManagerViewModel(private val addUserRssUseCase: AddUserRssUseCase) : ViewModel() {

    val rssManagerPublisher: MutableLiveData<RssManagerUiState> by lazy {
        MutableLiveData<RssManagerUiState>()
    }

    fun saveRss(url:String, name:String){
        rssManagerPublisher.value = RssManagerUiState(true)

        viewModelScope.launch(Dispatchers.IO){
            addUserRssUseCase.execute(url, name)
            rssManagerPublisher.postValue(
                RssManagerUiState(
                    isSuccess = true
 issue10_rss_form
                )
            )
        }
    }

issue11_get_user_rss
    fun deleteRss(url:String){
        viewModelScope.launch (Dispatchers.IO){
            deleteRssUseCase.execute(url)
        }
    }

    data class RssManagerFeedUiState(
        val isLoading: Boolean = false,
        val rssFeed: List<UserRss> = emptyList()
    )

    data class RssManagerUiState(
        val isSuccess: Boolean = false
        )

 issue10_rss_form
}