package com.seif.learningcompose

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class Variable {
    companion object {
        var previousSelectedPosition = -1
        val uiState: MutableState<UiState> = mutableStateOf(UiState())

        fun simulateApiCall(){
                uiState.value = UiState(isLoading = true)
                try {
                     uiState.value = UiState(repos = createReposList())
                }
                catch (e:Exception){
                    uiState.value = UiState(error = "Error: ${e.message}")
                }

        }

        fun createReposList(): List<TrendingRepositoriesEntity> {
            return listOf(
                TrendingRepositoriesEntity(
                    1,
                    "seif",
                    "https://picsum.photos/id/237/200/300",
                    "seif repository description",
                    100,
                    "Kotlin",
                    "#000000",
                    "SeifRepo",
                    50,
                    "test url",
                    1662292866135L,
                    mutableStateOf(false)
                ),
                TrendingRepositoriesEntity(
                    1,
                    "seif",
                    "https://picsum.photos/id/237/200/300",
                    "seif repository description",
                    100,
                    "Kotlin",
                    "#000000",
                    "SeifRepo",
                    50,
                    "test url",
                    1662292866135L,
                    mutableStateOf(false)
                ),
                TrendingRepositoriesEntity(
                    1,
                    "seif",
                    "https://picsum.photos/id/237/200/300",
                    "seif repository description",
                    100,
                    "Kotlin",
                    "#000000",
                    "SeifRepo",
                    50,
                    "test url",
                    1662292866135L,
                    mutableStateOf(false)
                ),
                TrendingRepositoriesEntity(
                    1,
                    "seif",
                    "https://picsum.photos/id/237/200/300",
                    "seif repository description",
                    100,
                    "Kotlin",
                    "#000000",
                    "SeifRepo",
                    50,
                    "test url",
                    1662292866135L,
                    mutableStateOf(false)
                ),
                TrendingRepositoriesEntity(
                    1,
                    "seif",
                    "https://picsum.photos/id/237/200/300",
                    "seif repository description",
                    100,
                    "Kotlin",
                    "#000000",
                    "SeifRepo",
                    50,
                    "test url",
                    1662292866135L,
                    mutableStateOf(false)
                ),
                TrendingRepositoriesEntity(
                    1,
                    "seif",
                    "https://picsum.photos/id/237/200/300",
                    "seif repository description",
                    100,
                    "Kotlin",
                    "#000000",
                    "SeifRepo",
                    50,
                    "test url",
                    1662292866135L,
                    mutableStateOf(false)
                ),
                TrendingRepositoriesEntity(
                    1,
                    "seif",
                    "https://picsum.photos/id/237/200/300",
                    "seif repository description",
                    100,
                    "Kotlin",
                    "#000000",
                    "SeifRepo",
                    50,
                    "test url",
                    1662292866135L,
                    mutableStateOf(false)
                ),
                TrendingRepositoriesEntity(
                    1,
                    "seif",
                    "https://picsum.photos/id/237/200/300",
                    "seif repository description",
                    100,
                    "Kotlin",
                    "#000000",
                    "SeifRepo",
                    50,
                    "test url",
                    1662292866135L,
                    mutableStateOf(false)
                ),
                TrendingRepositoriesEntity(
                    1,
                    "seif",
                    "https://picsum.photos/id/237/200/300",
                    "seif repository description",
                    100,
                    "Kotlin",
                    "#000000",
                    "SeifRepo",
                    50,
                    "test url",
                    1662292866135L,
                    mutableStateOf(false)
                ),
                TrendingRepositoriesEntity(
                    1,
                    "seif",
                    "https://picsum.photos/id/237/200/300",
                    "seif repository description",
                    100,
                    "Kotlin",
                    "#000000",
                    "SeifRepo",
                    50,
                    "test url",
                    1662292866135L,
                    mutableStateOf(false)
                ),
                TrendingRepositoriesEntity(
                    1,
                    "seif",
                    "https://picsum.photos/id/237/200/300",
                    "seif repository description",
                    100,
                    "Kotlin",
                    "#000000",
                    "SeifRepo",
                    50,
                    "test url",
                    1662292866135L,
                    mutableStateOf(false)
                ),
                TrendingRepositoriesEntity(
                    1,
                    "seif",
                    "https://picsum.photos/id/237/200/300",
                    "seif repository description",
                    100,
                    "Kotlin",
                    "#000000",
                    "SeifRepo",
                    50,
                    "test url",
                    1662292866135L,
                    mutableStateOf(false)
                )
            )
        }

    }
    data class UiState(
        val isLoading: Boolean = false,
        val repos: List<TrendingRepositoriesEntity> = emptyList(),
        val error: String = "",
        val isSwipingToRefresh: Boolean = false,
        val isRetry: Boolean = false
    )

}