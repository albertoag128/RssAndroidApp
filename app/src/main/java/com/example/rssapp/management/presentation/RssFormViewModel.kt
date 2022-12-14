package com.example.rssapp.management.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rssapp.management.domain.AddUserRssUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RssFormViewModel(private val addUserRssUseCase: AddUserRssUseCase) : ViewModel() {

    val rssManagerPublisher: MutableLiveData<RssManagerUiState> by lazy {
        MutableLiveData<RssManagerUiState>()
    }

    fun saveRss(url:String, name:String){
        rssManagerPublisher.value = RssManagerUiState(true)

        viewModelScope.launch(Dispatchers.IO){
            addUserRssUseCase.invoke(url, name)
            rssManagerPublisher.postValue(
                RssManagerUiState(
                    isSuccess = true
                )
            )
        }
    }

    data class RssManagerUiState(
        val isSuccess: Boolean = false
        )

}