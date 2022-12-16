package com.barabasizsolt.collapsingtoolbar.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalDimens = staticCompositionLocalOf { AppDimens() }

data class AppDimens(
    val screenPadding: Dp = 20.dp,
    val contentPadding: Dp = 8.dp,
    val doubleContentPadding: Dp = 16.dp,
    val smallPadding: Dp = 4.dp,

    val headerHeight: Dp = 250.dp,
    val toolbarHeight: Dp = 56.dp,

    val titlePaddingStart: Dp = 16.dp,
    val titlePaddingEnd: Dp = 72.dp,

    val iconSize: Dp = 36.dp
)