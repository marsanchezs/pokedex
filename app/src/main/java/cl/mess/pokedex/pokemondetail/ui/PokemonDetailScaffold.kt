package cl.mess.pokedex.pokemondetail.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import cl.mess.pokedex.pokemondetail.domain.model.PokemonDetail
import cl.mess.pokedex.pokemondetail.presentation.PokemonDetailViewModel
import cl.mess.pokedex.pokemondetail.ui.composables.PokemonDetailContent
import cl.mess.pokedex.ui.composables.PokemonTopBar
import cl.mess.pokedex.utils.getPokemonTypeColor

@Composable
fun PokemonDetailScaffold(
    pokemonDetail: PokemonDetail,
    onBack: () -> Unit,
    viewModel: PokemonDetailViewModel
) {
    //val isFavorite = viewModel.favoriteState.collectAsState(initial = false)
    val backgroundColor = getPokemonTypeColor(pokemonDetail = pokemonDetail)
    val context = LocalContext.current
    val shareData = viewModel.shareData.collectAsState()
    val isFavorite by viewModel.isPokemonFavorite.collectAsState()
    LaunchedEffect(pokemonDetail.name) {
        viewModel.checkIfPokemonIsFavorite(pokemonName = pokemonDetail.name)
        viewModel.setShareData(pokemonDetail = pokemonDetail)
    }

    Scaffold(
        topBar = {
            PokemonTopBar(
                onBack = onBack,
                text = pokemonDetail.name,
                actions = {
                    IconButton(
                        onClick = {
                            sharePokemon(
                                shareText = shareData.value.first,
                                imageUrl = shareData.value.second,
                                context = context
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = "Share",
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = { viewModel.toggleFavorite(pokemon = pokemonDetail) }
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = backgroundColor
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            PokemonDetailContent(
                sprites = pokemonDetail.sprites,
                name = pokemonDetail.name,
                abilities = pokemonDetail.abilities,
                types = pokemonDetail.types,
                backgroundColor = backgroundColor
            )
        }
    }
}

private fun sharePokemon(
    shareText: String,
    imageUrl: String,
    context: Context
) {
    val message = "Check out this Pokémon: ${shareText}. Image: $imageUrl"

    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}
