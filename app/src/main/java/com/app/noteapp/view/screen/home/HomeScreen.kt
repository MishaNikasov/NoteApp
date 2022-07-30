package com.app.noteapp.view.screen.home

import androidx.compose.runtime.*
import com.app.noteapp.navigation.Router
import com.app.noteapp.view.screen.home.model.HomeScreenEvent
import com.app.noteapp.view.screen.home.model.HomeViewState
import com.app.noteapp.view.screen.home.view.HomeScreenContent
import com.app.presentation.widget.Loader

@Composable
fun HomeScreen(
    router: Router,
    homeViewModel: HomeViewModel
) {
    val homeViewState by homeViewModel.homeViewState

    when (homeViewState) {
        HomeViewState.Loading -> Loader()
        is HomeViewState.EmptyContent -> HomeScreenContent(
            list = emptyList(),
            onItemCreate = { router.openNote(null) },
            listArrangement = (homeViewState as HomeViewState.EmptyContent).listArrangement,
            onListArrangementChange = { state ->
                homeViewModel.obtainEvent(HomeScreenEvent.ChangeListArrangement(state))
            },
            onItemSelect = {},
            onSearch = {}
        )
        is HomeViewState.NoteListContent -> HomeScreenContent(
            list = (homeViewState as HomeViewState.NoteListContent).content,
            onItemSelect = { router.openNote(it.id) },
            onItemCreate = { router.openNote(null) },
            listArrangement = (homeViewState as HomeViewState.NoteListContent).listArrangement,
            onListArrangementChange = { state ->
                homeViewModel.obtainEvent(HomeScreenEvent.ChangeListArrangement(state))
            },
            onSearch = { query ->
                homeViewModel.obtainEvent(HomeScreenEvent.SearchNotes(query))
            }
        )
    }

    LaunchedEffect(true) {
        homeViewModel.obtainEvent(HomeScreenEvent.FetchNotes)
    }
}
