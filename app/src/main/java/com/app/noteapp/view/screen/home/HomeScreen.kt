package com.app.noteapp.view.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.app.noteapp.navigation.Router
import com.app.noteapp.view.screen.home.view.HomeScreenContent
import com.app.presentation.widget.Loader

@Composable
fun HomeScreen(
    router: Router,
    homeViewModel: HomeViewModel
) {
    val homeViewState by remember { homeViewModel.homeViewState }

    when (homeViewState) {
        HomeViewState.EmptyContent -> Loader()
        HomeViewState.Loading -> Loader()
        is HomeViewState.ShowContent -> HomeScreenContent(
            list = (homeViewState as HomeViewState.ShowContent).content,
            onItemSelect = { router.openNote(it.id) },
            onItemCreate = { router.openNote(null) },
            onSearch = { }
        )
    }

    LaunchedEffect(true) {
        homeViewModel.obtainEvent(HomeScreenEvent.FetchNotes)
    }
}
