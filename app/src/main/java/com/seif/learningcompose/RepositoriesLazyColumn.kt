package com.seif.learningcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun RepositoriesLazyColumn(reposList: List<TrendingRepositoriesEntity>) {
    androidx.compose.foundation.lazy.LazyColumn(modifier = Modifier.padding(horizontal = 10.dp)) {
        itemsIndexed(reposList) { index, item ->
            RepoItemExpandable(
                repositoriesEntity = item,
                index,
                onItemClick = {
                    if (Variable.previousSelectedPosition == -1) {
                        Variable.previousSelectedPosition = it
                        reposList[it].isExpanded.value = true
                    } else if (it == Variable.previousSelectedPosition) {
                        reposList[it].isExpanded.value = !reposList[it].isExpanded.value
                    } else { // not equal
                        reposList[it].isExpanded.value = true
                        reposList[Variable.previousSelectedPosition].isExpanded.value = false
                        Variable.previousSelectedPosition = it
                    }
                })
        }
    }
}