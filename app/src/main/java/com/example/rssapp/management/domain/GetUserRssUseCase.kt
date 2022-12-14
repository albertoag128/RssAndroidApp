package com.example.rssapp.management.domain

import kotlinx.coroutines.flow.Flow


class GetUserRssUseCase (private val repository: UserRssRepository){
    suspend fun invoke(): Flow<List<UserRss>> {
        return repository.getUserRss()
    }
}