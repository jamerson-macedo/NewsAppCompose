package com.example.newsappcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcompose.domain.usecase.app_entry.AppEntryUseCases
import com.example.newsappcompose.presentation.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCases: AppEntryUseCases) : ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set
    // startnavigation
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        useCases.readAppEntry().onEach {
            if (it) {
                // se for verdadeiro vai pra pagina principal
                startDestination = Route.NewsNavigation.route
            } else {
                startDestination = Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)

    }

}