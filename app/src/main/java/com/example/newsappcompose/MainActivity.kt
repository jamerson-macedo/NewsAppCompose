package com.example.newsappcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.example.newsappcompose.domain.usecase.app_entry.AppEntryUseCases
import com.example.newsappcompose.presentation.nvgraph.NavGraph
import com.example.newsappcompose.ui.theme.NewsAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewmodel by viewModels<MainViewModel>()

    @Inject
    lateinit var useCases: AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            useCases.readAppEntry().collect {
                Log.e("testando", it.toString())
            }

        }

        // imagem fica do tamanhbo total pegando o actionbar
        // tem que mudar no thema tbm
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition { viewmodel.splashCondition }
        }
        setContent {
            NewsAppTheme {
                // retorna um boolean
                val isSystemDark = isSystemInDarkTheme()
                val systemControler = rememberSystemUiController()
                // acontece sempre uma vez apos a recomposicao de tela
                SideEffect {
                    // deixando transparent
                    systemControler.setSystemBarsColor(
                        color = Color.Transparent, darkIcons = !isSystemDark
                    )

                }
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    val startDestination = viewmodel.startDestination
                    NavGraph(startDestination = startDestination)

                }

            }
        }
    }
}

