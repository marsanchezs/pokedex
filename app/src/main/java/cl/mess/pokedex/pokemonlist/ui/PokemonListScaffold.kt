package cl.mess.pokedex.pokemonlist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cl.mess.pokedex.navigation.PokemonRoutes
import cl.mess.pokedex.pokemonlist.presentation.PokemonListViewModel
import cl.mess.pokedex.pokemonlist.ui.composables.PokemonListSearchBar
import cl.mess.pokedex.pokemonlist.ui.composables.PokemonListTab
import cl.mess.pokedex.pokemonlist.ui.composables.PokemonSearchHistoryList
import cl.mess.pokedex.ui.composables.PokemonErrorPopup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScaffold(
    viewModel: PokemonListViewModel,
    navController: NavHostController
) {
    val isConnected by viewModel.isConnected.collectAsState()
    var searchText by remember { mutableStateOf(value = "") }
    var selectedTab by remember { mutableIntStateOf(value = 0) }
    val searchHistory = viewModel.searchHistory.collectAsState().value
    val filteredTerms = if (searchText.isEmpty()) emptyList() else {
        searchHistory.filter { term ->
            term.searchTerm.contains(other = searchText, ignoreCase = true)
        }
    }
    var clickCount by remember { mutableIntStateOf(value = 0) }
    var showPopup by remember { mutableStateOf(value = false) }

    LaunchedEffect(Unit) {
        viewModel.loadAllSearchHistory()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    PokemonListSearchBar(
                        text = searchText,
                        onTextChange = { searchText = it },
                        onSearchSubmit = { pokemonName ->
                            navController.navigate(route = "${PokemonRoutes.PokemonDetail.path}?pokemonName=${pokemonName}")
                            viewModel.saveSearchTerm(pokemonName)
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {

            Column(modifier = Modifier.fillMaxSize()) {
                TabRow(
                    selectedTabIndex = selectedTab,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    PokemonListTab(
                        text = "All",
                        icon = Icons.AutoMirrored.Filled.List,
                        isSelected = selectedTab == 0,
                        onClick = { selectedTab = 0 }
                    )
                    PokemonListTab(
                        text = "Favorites",
                        icon = Icons.Default.Favorite,
                        isSelected = selectedTab == 1,
                        onClick = { selectedTab = 1 }
                    )
                }

                when (selectedTab) {
                    0 -> PokemonListContent(
                        navController = navController,
                        viewModel = viewModel
                    )

                    1 -> PokemonFavoritesContent(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }

            if (!isConnected) {
                PokemonSearchHistoryList(
                    filteredTerms = filteredTerms,
                    onSearchTermClick = { searchTerm ->
                        searchText = searchTerm
                        clickCount++
                        showPopup = true
                    }
                )
            }
        }
    }

    if (showPopup) {
        PokemonErrorPopup(
            message = if (clickCount % 2 == 0)
                "Error 500: Internal server error" else "Error 400: Bad request",
            onDismiss = { showPopup = false }
        )
    }
}



