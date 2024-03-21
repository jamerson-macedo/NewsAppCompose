package com.example.newsappcompose.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsappcompose.domain.model.Article

class SearchNewsPagingSource(
    private val api: NewsApi,
    val sources: String,
    val search: String
) :
    PagingSource<Int, Article>() {
    private var totalNews = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = api.searchNews(country = sources, page = page, search = search)
            // tamanho da lista que vem da apu
            totalNews += newsResponse.articles.size
            // pegando artigos nao repitidos
            val articles = newsResponse.articles.distinctBy { it.title }
            // se chegou no final entao retorna nullo se n acrescenta +1
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNews == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)

        }
    }

}
