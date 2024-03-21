package com.example.newsappcompose.presentation.search

sealed class SearchEvents {
    data class UpdateSearchQuery(val searchQuery: String):SearchEvents()
    object SearchNews:SearchEvents()
}