package cl.mess.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.mess.pokedex.navigation.Constants.POKEMON_NAME
import cl.mess.pokedex.pokemondetail.presentation.PokemonDetailViewModel
import cl.mess.pokedex.pokemondetail.ui.PokemonDetailScreen
import cl.mess.pokedex.pokemonlist.presentation.PokemonListViewModel
import cl.mess.pokedex.pokemonlist.ui.PokemonListScaffold

@Composable
fun NavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PokemonRoutes.PokemonList.path
    ) {
        composable(route = PokemonRoutes.PokemonList.path) {
            val viewModel: PokemonListViewModel = hiltViewModel()
            PokemonListScaffold(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(
            route = "${PokemonRoutes.PokemonDetail.path}?pokemonName={$POKEMON_NAME}",
            arguments = listOf(
                navArgument(POKEMON_NAME) { nullable = true }
            )
        ) { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString(POKEMON_NAME)
            val viewModel: PokemonDetailViewModel = hiltViewModel()
            PokemonDetailScreen(
                viewModel = viewModel,
                navController = navController,
                pokemonName = pokemonName,
            )
        }
    }
}
