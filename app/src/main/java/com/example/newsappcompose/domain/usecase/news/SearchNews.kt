package com.example.newsappcompose.domain.usecase.news

import androidx.paging.PagingData
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(private val repository: NewsRepository) {
    operator fun invoke(source: List<String>, search: String): Flow<PagingData<Article>> {
        return repository.searchNews(search, source)
    }
}