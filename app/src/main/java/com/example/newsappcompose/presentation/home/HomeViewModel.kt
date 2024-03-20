package com.example.newsappcompose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsappcompose.domain.usecase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUsesCases: NewsUseCases) : ViewModel() {

    val news=newsUsesCases.getNews(source = listOf("bbc-news","abc-news")).cachedIn(viewModelScope)
}