package cl.mess.pokedex.pokemondetail.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import cl.mess.pokedex.pokemondetail.presentation.PokemonDetailUiState
import cl.mess.pokedex.pokemondetail.presentation.PokemonDetailViewModel
import cl.mess.pokedex.ui.composables.PokemonLoading
import cl.mess.pokedex.ui.composables.pokemonerror.PokemonError

@Composable
fun PokemonDetailScreen(
    navController: NavHostController,
    pokemonName: String?,
    viewModel: PokemonDetailViewModel
) {
    val pokemonDetail by viewModel.pokemonDetail.collectAsState()
    val uiState by viewModel.viewState.collectAsState()

    LaunchedEffect(pokemonName) {
        if (pokemonName != null) viewModel.getPokemonDetail(pokemonName = pokemonName)
    }

    when (uiState) {
        is PokemonDetailUiState.LoadingUiState -> {
            PokemonLoading()
        }

        is PokemonDetailUiState.ErrorUiState -> {
            PokemonError(
                onBack = { navController.popBackStack() },
                onRetry = {
                    if (pokemonName != null) viewModel.getPokemonDetail(pokemonName = pokemonName)
                }
            )
        }

        is PokemonDetailUiState.SuccessUiState -> {
            PokemonDetailScaffold(
                viewModel = viewModel,
                pokemonDetail = pokemonDetail,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
