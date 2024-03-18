package com.example.newsappcompose.presentation.nvgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.newsappcompose.presentation.onboarding.OnBoardingScreen
import com.example.newsappcompose.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewmodel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = {
                    viewmodel.onEvent(it)
                })
            }
        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route
        ) {
            composable(route = Route.NewsNavigationScreen.route){
                Text(text = Route.NewsNavigationScreen.route)
            }
        }

    }

}