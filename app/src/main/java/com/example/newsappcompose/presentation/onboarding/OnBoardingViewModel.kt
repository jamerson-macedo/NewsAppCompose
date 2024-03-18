package com.example.newsappcompose.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsappcompose.domain.usecase.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val userCases: AppEntryUseCases) :
    ViewModel() {
    fun onEvent(onBoardingEvent: OnBoardingEvent) {
            when(onBoardingEvent){
                is OnBoardingEvent.SaveAppEntry->{
                    saveAppEntry()
                }
            }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            userCases.saveAppEntry()
        }

    }

}