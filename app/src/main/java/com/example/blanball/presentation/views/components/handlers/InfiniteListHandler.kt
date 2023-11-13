package com.example.blanball.presentation.views.components.handlers

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow

@Composable
internal fun InfiniteListHandler(
    lazyListState: LazyListState,
    onLoadMore: () -> Unit
) {
    val loadThreshold = 0.8 // Процент от конца списка для начала загрузки
    val visibleItemsInfo = lazyListState.layoutInfo.visibleItemsInfo
    val lastVisibleItemIndex = visibleItemsInfo.maxByOrNull { it.index }?.index ?: 0
    val totalItems = lazyListState.layoutInfo.totalItemsCount

    if (lastVisibleItemIndex >= loadThreshold * totalItems) {
        onLoadMore()
    }

    LaunchedEffect(lazyListState) {
        val snapshotFlow = snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo }
        snapshotFlow.collect { visibleItems ->
            val lastVisibleIndex = visibleItems.maxByOrNull { it.index }?.index ?: 0
            if (lastVisibleIndex >= loadThreshold * totalItems) {
                onLoadMore()
            }
        }
    }
}
