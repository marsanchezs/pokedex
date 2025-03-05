package cl.mess.pokedex.pokemonlist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cl.mess.pokedex.R
import cl.mess.pokedex.navigation.PokemonRoutes
import cl.mess.pokedex.pokemonlist.presentation.PokemonListViewModel
import cl.mess.pokedex.pokemonlist.ui.composables.PokemonListItem
import cl.mess.pokedex.ui.composables.AttrsPokemonText
import cl.mess.pokedex.ui.composables.AttrsPokemonTextFontFamily
import cl.mess.pokedex.ui.composables.PokemonText

@Composable
fun PokemonFavoritesContent(
    viewModel: PokemonListViewModel,
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        viewModel.refreshFavorites()
    }

    val favorites by viewModel.favorites.collectAsState()

    if (favorites.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            PokemonText(
                attrs = AttrsPokemonText(
                    text = "No favorite Pokémon found",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Red,
                    attrsFontFamily = AttrsPokemonTextFontFamily(
                        resId = R.font.lemonmilk_medium
                    )
                )
            )
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(favorites.size) { index ->
                favorites[index].let { pokemon ->
                    PokemonListItem(
                        imageUrl = pokemon.frontImageUrl,
                        name = pokemon.name,
                        onClick = {
                            navController.navigate(route = "${PokemonRoutes.PokemonDetail.path}?pokemonName=${pokemon.name}")
                        }
                    )
                }
            }
        }
    }
}
