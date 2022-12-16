package com.barabasizsolt.collapsingtoolbar.ui.catalog

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.barabasizsolt.collapsingtoolbar.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.barabasizsolt.collapsingtoolbar.ui.theme.AppTheme

private const val titleFontScaleStart = 1f
private const val titleFontScaleEnd = 0.66f

@Composable
internal fun Title(
    listState: LazyListState,
    showToolBar: Boolean,
    headerHeightPx: Float,
    toolbarHeightPx: Float,
) {
    var titleHeightPx by remember { mutableStateOf(value = 0f) }
    var titleWidthPx by remember { mutableStateOf(value = 0f) }

    val headerHeight = AppTheme.dimens.headerHeight
    val toolbarHeight = AppTheme.dimens.toolbarHeight
    val titlePaddingStart = AppTheme.dimens.titlePaddingStart
    val titlePaddingEnd = AppTheme.dimens.titlePaddingEnd
    val paddingMedium = AppTheme.dimens.doubleContentPadding

    Text(
        text = stringResource(id = R.string.black_forest),
        color = AppTheme.colors.onBackground,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .statusBarsPadding()
            .graphicsLayer {
                val collapseRange: Float = headerHeightPx - toolbarHeightPx

                val collapseFraction: Float = if (!showToolBar)
                    (listState.firstVisibleItemScrollOffset / collapseRange).coerceIn(0f, 1f)
                else 1f

                val scaleXY = lerp(
                    start = titleFontScaleStart.dp,
                    stop = titleFontScaleEnd.dp,
                    fraction = collapseFraction
                )

                val titleExtraStartPadding = titleWidthPx.toDp() * (1 - scaleXY.value) / 2f

                val titleYFirstInterpolatedPoint = lerp(
                    start = headerHeight - titleHeightPx.toDp() - paddingMedium,
                    stop = headerHeight / 2f,
                    fraction = collapseFraction
                )

                val titleXFirstInterpolatedPoint = lerp(
                    start = titlePaddingStart,
                    stop = (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    fraction = collapseFraction
                )

                val titleYSecondInterpolatedPoint = lerp(
                    start = headerHeight / 4,
                    stop = toolbarHeight / 2 - titleHeightPx.toDp() / 2,
                    fraction = collapseFraction
                )

                val titleXSecondInterpolatedPoint = lerp(
                    start = (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    stop = titlePaddingEnd - titleExtraStartPadding,
                    fraction = collapseFraction
                )

                val titleY = lerp(
                    start = titleYFirstInterpolatedPoint,
                    stop = titleYSecondInterpolatedPoint,
                    fraction = collapseFraction
                )

                val titleX = lerp(
                    start = titleXFirstInterpolatedPoint,
                    stop = titleXSecondInterpolatedPoint,
                    fraction = collapseFraction
                )

                translationY = titleY.toPx()
                translationX = titleX.toPx()
                scaleX = scaleXY.value
                scaleY = scaleXY.value
            }
            .onGloballyPositioned { coordinates ->
                titleHeightPx = coordinates.size.height.toFloat()
                titleWidthPx = coordinates.size.width.toFloat()
            }
    )
}