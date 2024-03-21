package com.example.newsappcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsappcompose.data.remote.NewsApi
import com.example.newsappcompose.data.remote.NewsPagingSource
import com.example.newsappcompose.data.remote.SearchNewsPagingSource
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val newsApi: NewsApi) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        // queremos carregar 10 paginas
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                NewsPagingSource(newsApi, sources.joinToString(separator = ","))

            }
        ).flow
    }

    override fun searchNews(search: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi,
                    sources.joinToString(separator = ","),
                    search = search
                )

            }
        ).flow
    }
}