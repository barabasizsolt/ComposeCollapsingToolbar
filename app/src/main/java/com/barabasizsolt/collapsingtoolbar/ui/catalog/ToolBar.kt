package com.barabasizsolt.collapsingtoolbar.ui.catalog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.barabasizsolt.collapsingtoolbar.ui.util.statusBarInsetDp

@Composable
internal fun Toolbar(
    modifier: Modifier = Modifier,
    showToolBar: Boolean
) {
    AnimatedVisibility(
        visible = showToolBar,
        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500)),
        modifier = modifier
    ) {
        TopAppBar(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
                .statusBarsPadding(),
            navigationIcon = {
                IconButton(
                    onClick = { /*TODO: Implement it*/ },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .size(size = 36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            },
            title = { },
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        )
    }
}