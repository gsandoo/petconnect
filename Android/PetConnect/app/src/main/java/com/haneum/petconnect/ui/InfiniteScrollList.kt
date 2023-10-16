package com.haneum.petconnect.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.haneum.petconnect.data.Board

@Composable
fun InfiniteScrollList(
    boards: List<Board>,
    loadMoreItems: () -> Unit,
    onPostClick: (Board) -> Unit,
    filter: String
) {

    val listState = rememberLazyListState()


    LazyColumn(
        state = listState,
        content = {
            items(boards){item ->
                if(item.category == filter || filter == ""){
                    Post(
                        post = item,
                        modifier = Modifier.padding(10.dp),
                        onPostClick = {onPostClick(item)}
                    )
                    Divider(
                        color = Color.LightGray.copy(alpha = 0.5f),
                        thickness = 4.dp
                    )
                }
            }
            if(listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size >= boards.size -1){
                loadMoreItems()
            }
        }
    )
}