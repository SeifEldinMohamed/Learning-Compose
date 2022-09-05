package com.seif.learningcompose

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.seif.learningcompose.Variable.Companion.previousSelectedPosition
import com.seif.learningcompose.Variable.Companion.simulateApiCall
import com.seif.learningcompose.Variable.Companion.uiState

@Composable
fun ReposListScreen() {
    // val reposList: List<TrendingRepositoriesEntity> = createReposList()
    Column {
        OptionMenu()
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
        if (stateValue.repos.isNotEmpty())
            RepositoriesLazyColumn(reposList = stateValue.repos)

    }
    // RepositoriesLazyColumn(reposList = reposList)

}


@Composable
fun OptionMenu() {
    var showMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(
                "Trending",
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
        },
        backgroundColor = Color.White,
        actions = {
            /*
            IconButton(onClick = {
                Toast.makeText(context, "Favorite", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Favorite, "")
            }
             */

            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.MoreVert, "")
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(onClick = {
                    Toast.makeText(context, "sorting by name", Toast.LENGTH_SHORT).show()
                    showMenu = false
                }
                ) {
                    Text(text = "Sort By Name", Modifier.padding(horizontal = 10.dp))
                }
                DropdownMenuItem(onClick = {
                    Toast.makeText(context, "sorting by stars", Toast.LENGTH_SHORT).show()
                    showMenu = false
                }) {
                    Text(text = "Sort By Stars", Modifier.padding(horizontal = 10.dp))
                }
            }
        }
    )
}

@Composable
fun RepositoriesLazyColumn(reposList: List<TrendingRepositoriesEntity>) {
    androidx.compose.foundation.lazy.LazyColumn(modifier = Modifier.padding(top = 10.dp)) {
        itemsIndexed(reposList) { index, item ->
            RepoItemExpandable(
                repositoriesEntity = item,
                index,
                onItemClick = {
                    if (previousSelectedPosition == -1) {
                        previousSelectedPosition = it
                        reposList[it].isExpanded.value = true
                    } else if (it == previousSelectedPosition) {
                        reposList[it].isExpanded.value = !reposList[it].isExpanded.value
                    } else { // not equal
                        reposList[it].isExpanded.value = true
                        reposList[previousSelectedPosition].isExpanded.value = false
                        previousSelectedPosition = it
                    }
                })
        }
    }
}


