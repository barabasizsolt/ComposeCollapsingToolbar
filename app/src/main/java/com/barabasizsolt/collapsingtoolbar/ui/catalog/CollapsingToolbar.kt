package com.barabasizsolt.collapsingtoolbar.ui.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.barabasizsolt.collapsingtoolbar.ui.theme.AppTheme
import com.barabasizsolt.collapsingtoolbar.ui.theme.CollapsingToolbarTheme

@Composable
fun CollapsingToolbar(modifier: Modifier = Modifier) {
    val listState: LazyListState = rememberLazyListState()

    val headerHeightPx = with(receiver = LocalDensity.current) {
        AppTheme.dimens.headerHeight.toPx()
    }
    val toolbarHeightPx = with(receiver = LocalDensity.current) {
        AppTheme.dimens.toolbarHeight.toPx()
    }

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
            .background(color = AppTheme.colors.background)
    ) {
        Header(headerHeightPx = headerHeightPx, listState = listState)
        Body(listState = listState)
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