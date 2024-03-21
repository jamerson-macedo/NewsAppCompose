package com.example.newsappcompose.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsappcompose.domain.usecase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {
    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(events: SearchEvents) {
        when (events) {
            is SearchEvents.SearchNews -> {
                searchNews()
            }

            is SearchEvents.UpdateSearchQuery -> {
                _state.value = state.value.copy(events.searchQuery)
            }

            else -> {}
        }
    }

    private fun searchNews() {
        val articles = newsUseCases.searchNews(
            search = state.value.searchQuery,
            source = listOf("bbc-news", "abc-news")
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles)
    }
}