package com.barabasizsolt.collapsingtoolbar.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barabasizsolt.collapsingtoolbar.ui.theme.CollapsingToolbarTheme

val headerHeight = 250.dp
val toolbarHeight = 56.dp

val paddingMedium = 16.dp

@Composable
fun CollapsingToolbar(
    modifier: Modifier = Modifier
) {
    val listState: LazyListState = rememberLazyListState()

    val headerHeightPx = with(receiver = LocalDensity.current) { headerHeight.toPx() }
    val toolbarHeightPx = with(receiver = LocalDensity.current) { toolbarHeight.toPx() }

    val toolbarBottom = headerHeightPx - toolbarHeightPx
    val showToolbar by remember {
        derivedStateOf {
            if (listState.firstVisibleItemIndex == 0) {
                listState.firstVisibleItemScrollOffset >= toolbarBottom
            } else true
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Header(
            headerHeight = headerHeight,
            listState = listState
        )
        Body(
            headerHeight = headerHeight,
            listState = listState
        )
        Toolbar(showToolBar = showToolbar)
        Title(
            headerHeightPx = headerHeightPx,
            toolbarHeightPx = toolbarHeightPx,
            listState = listState,
            showToolBar = showToolbar
        )
    }
}

@Preview
@Composable
fun CollapsingToolBarPreview() {
    CollapsingToolbarTheme {
        CollapsingToolbar()
    }
}