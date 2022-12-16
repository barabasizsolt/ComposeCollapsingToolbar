package com.barabasizsolt.collapsingtoolbar.ui.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.barabasizsolt.collapsingtoolbar.R

@Composable
internal fun Header(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    headerHeight: Dp,
) {
    val headerHeightPx = with(receiver = LocalDensity.current) { headerHeight.toPx() }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = headerHeight)
            .graphicsLayer {
                /*Parallax effect*/
                if (listState.firstVisibleItemIndex == 0) {
                    translationY = -listState.firstVisibleItemScrollOffset.toFloat() / 3
                    alpha = (-1f / headerHeightPx) * listState.firstVisibleItemScrollOffset + 1
                }
                else {
                    translationY = 0f
                    alpha = 0f
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        /*Bottom gradient*/
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Transparent,
                        MaterialTheme.colors.background
                    )
                )
            )
        )
    }
}