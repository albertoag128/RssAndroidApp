package com.example.rssapp.management.presentation

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rssapp.management.domain.AddUserRssUseCase
import com.example.rssapp.management.domain.DeleteRssUseCase
import com.example.rssapp.management.domain.GetUserRssUseCase
import com.example.rssapp.management.domain.UserRss
import kotlinx.coroutines.launch

class RssDataStoreViewModel (
    private val getUserRssUseCase: GetUserRssUseCase,
    private val addUserRssUseCase: AddUserRssUseCase,
    private val deleteUserRssUseCase: DeleteRssUseCase
        ) : ViewModel() {

    private val _uiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState

    fun obtainUserRssList() {
        viewModelScope.launch {
            getUserRssUseCase.invoke()
                .collect { userRss ->
                        _uiState.postValue(
                            UiState(
                                isLoading = false,
                                userRssList = userRss
                            )
                        )
                    }
                }
        }

    fun saveUserRss(url: String, name: String) {
        viewModelScope.launch {
            addUserRssUseCase.invoke(url, name)
        }
    }

    fun deleteUserRss(url: String){
        viewModelScope.launch {
            deleteUserRssUseCase.invoke(url)
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val userRssList: List<UserRss>? = null
    )
}