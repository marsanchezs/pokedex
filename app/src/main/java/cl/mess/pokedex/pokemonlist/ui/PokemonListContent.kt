package cl.mess.pokedex.pokemonlist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import cl.mess.pokedex.navigation.PokemonRoutes
import cl.mess.pokedex.pokemonlist.presentation.PokemonListViewModel
import cl.mess.pokedex.pokemonlist.ui.composables.PokemonListItem
import cl.mess.pokedex.ui.composables.PokemonLoading
import cl.mess.pokedex.ui.composables.pokemonerror.PokemonError

@Composable
fun PokemonListContent(
    viewModel: PokemonListViewModel,
    navController: NavHostController
) {
    val pokemonList = viewModel.pokemonList.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pokemonList.itemCount) { index ->
                pokemonList[index]?.let { pokemon ->
                    PokemonListItem(
                        imageUrl = pokemon.imageUrl,
                        name = pokemon.name,
                        onClick = {
                            navController.navigate(route = "${PokemonRoutes.PokemonDetail.path}?pokemonName=${pokemon.name}")
                        }
                    )
                }
            }
        }

        if (pokemonList.loadState.refresh is LoadState.Loading) {
            PokemonLoading()
        }

        if (pokemonList.loadState.refresh is LoadState.Error) {
            PokemonError(
                onBack = { navController.popBackStack() },
                onRetry = { pokemonList.retry() }
            )
        }
    }
}
