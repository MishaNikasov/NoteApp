package com.app.noteapp.view.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.app.noteapp.navigation.Router
import com.app.noteapp.view.screen.home.model.HomeScreenEvent
import com.app.noteapp.view.screen.home.model.HomeViewState
import com.app.noteapp.view.screen.home.view.HomeScreenContent
import com.app.presentation.widget.Loader
import com.app.util.ViewState

@Composable
fun HomeScreen(
    router: Router,
    viewModel: HomeViewModel
) {
    val viewState by viewModel.homeViewState.collectAsState()

    when (viewState) {
        is ViewState.Successes, ViewState.Empty -> HomeScreenContent(
            list = (viewState as ViewState.Successes<HomeViewState>).data.content,
            onItemSelect = { router.openNote(it.id) },
            onItemCreate = { router.openNote(null) },
            listArrangement = (viewState as ViewState.Successes).data.listArrangement,
            onListArrangementChange = { state ->
                viewModel.obtainEvent(HomeScreenEvent.ChangeListArrangement(state))
            },
            onSearch = { query ->
                viewModel.obtainEvent(HomeScreenEvent.SearchNotes(query))
            }
        )
        is ViewState.Error -> Loader()
        is ViewState.Loading -> Loader()
    }

    LaunchedEffect(true) {
        viewModel.obtainEvent(HomeScreenEvent.FetchNotes)
    }
}
