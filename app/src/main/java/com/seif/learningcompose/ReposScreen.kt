package com.seif.learningcompose

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.seif.learningcompose.Variable.Companion.uiState
import kotlinx.coroutines.delay

@Composable
fun ReposListScreen() {
    Column {
        OptionMenu()
        SwipeRefreshCompose()

    }
}


@Composable
fun SwipeRefreshCompose() {
    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) { // do your work here
            delay(1500)
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { refreshing = true },
    ) {
        val stateValue = uiState.value
        if (stateValue.error.isNotBlank()) {
            Toast.makeText(LocalContext.current, "Error: ${stateValue.error}", Toast.LENGTH_SHORT)
                .show()
        }
        if (stateValue.isLoading) {
            Column {
                repeat(10) {
                    AnimatedShimmer()
                }
            }
        }
        if (stateValue.repos.isNotEmpty()){
            val reposListStateValue by rememberSaveable { // to save state when configuration changed
                mutableStateOf(stateValue.repos)
            }
            RepositoriesLazyColumn(reposList = reposListStateValue)
        }
    }
}

/*
@Composable
fun SwipeRefreshCompose() {
    val context = LocalContext.current
    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = false), onRefresh = {
        Toast.makeText(context, "refreshed", Toast.LENGTH_SHORT).show()
    },
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                scale = true,
                backgroundColor = Color.White
            )
        }
    ) {

    }
}

 */
