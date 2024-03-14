package com.example.newsappcompose.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsappcompose.presentation.components.OnBoardingPage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardScreen() {
    Column(Modifier.fillMaxSize()) {
        val pagestate = rememberPagerState(0) {
            pages.size
        }
        val buttomState = remember {
            derivedStateOf {
                when (pagestate.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("back", "Next")
                    2 -> listOf("back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }
        HorizontalPager(state = pagestate) { index ->
            OnBoardingPage(page = pages[index])

        }

    }


}

@Preview
@Composable
private fun OnboardPreview() {
    OnBoardScreen()
}