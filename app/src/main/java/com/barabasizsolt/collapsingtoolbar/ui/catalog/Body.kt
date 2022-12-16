package com.barabasizsolt.collapsingtoolbar.ui.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.barabasizsolt.collapsingtoolbar.R
import com.barabasizsolt.collapsingtoolbar.ui.theme.AppTheme
import com.barabasizsolt.collapsingtoolbar.ui.util.navigationBarInsetDp
import com.barabasizsolt.collapsingtoolbar.ui.util.statusBarInsetDp

@Composable
internal fun Body(
    modifier: Modifier = Modifier,
    listState: LazyListState
) = LazyColumn(
    modifier = modifier,
    state = listState,
    horizontalAlignment = Alignment.CenterHorizontally,
    contentPadding = PaddingValues(
        bottom = navigationBarInsetDp,
        top = statusBarInsetDp / 2
    ),
    content = {
        item { Spacer(modifier = Modifier.height(height = AppTheme.dimens.headerHeight)) }
        bodyItem()
    }
)

private fun LazyListScope.bodyItem() = items(count = 10) {
    Card(
        modifier = Modifier.padding(all = AppTheme.dimens.doubleContentPadding),
        backgroundColor = AppTheme.colors.background
    ) {
        Column {
            Image(
                painter = painterResource(id = R.mipmap.item),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.aspectRatio(ratio = 2f)
            )
            Text(
                text = stringResource(id = R.string.lorem_ipsum),
                style = AppTheme.typography.body1,
                textAlign = TextAlign.Justify,
                color = AppTheme.colors.onBackground,
                modifier = Modifier.padding(all = AppTheme.dimens.contentPadding)
            )
        }
    }
}