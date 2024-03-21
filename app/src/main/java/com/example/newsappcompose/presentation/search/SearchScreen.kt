package com.example.newsappcompose.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsappcompose.presentation.Dimens.MediumPadding1
import com.example.newsappcompose.presentation.common.ArticleList
import com.example.newsappcompose.presentation.common.SearchBar
import com.example.newsappcompose.presentation.nvgraph.Route

@Composable
fun SearchScreen(state: SearchState, events: (SearchEvents) -> Unit, navigate: (String) -> Unit) {

    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding().fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { events(SearchEvents.UpdateSearchQuery(it)) },
            onSearch = { events(SearchEvents.SearchNews) })
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(articles = articles, onClick = {
                navigate(Route.DetailsScreen.route)
            })
        }
    }
}