package com.example.newsappcompose.presentation.common


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsappcompose.presentation.Dimens.IndicatorSize
import com.example.newsappcompose.ui.theme.BlueGray
import com.example.newsappcompose.ui.theme.NewsAppTheme

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pagesize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = BlueGray
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pagesize) { page ->
            Box(
                Modifier.size(IndicatorSize).clip(CircleShape)
                    .background(color = if (page == selectedPage) selectedColor else unSelectedColor)
            ) {

            }

        }

    }

}

@Preview
@Composable
private fun pagePreview() {
    NewsAppTheme {
        PageIndicator(pagesize = 2, selectedPage = 1)
    }
}
