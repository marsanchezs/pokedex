package cl.mess.pokedex.pokemondetail.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.pokedex.R
import cl.mess.pokedex.pokemondetail.domain.model.AbilityWrapper
import cl.mess.pokedex.pokemondetail.domain.model.SpriteImages
import cl.mess.pokedex.pokemondetail.domain.model.TypeWrapper
import cl.mess.pokedex.pokemondetail.ui.composables.pokemondetailcarousel.PokemonDetailCarousel
import cl.mess.pokedex.ui.composables.AttrsPokemonText
import cl.mess.pokedex.ui.composables.AttrsPokemonTextFontFamily
import cl.mess.pokedex.ui.composables.PokemonText
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PokemonDetailContent(
    sprites: SpriteImages,
    name: String,
    abilities: List<AbilityWrapper>,
    types: List<TypeWrapper>,
    backgroundColor: Color
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokemonDetailCarousel(
            pagerState = pagerState,
            sprites = sprites
        )

        Spacer(modifier = Modifier.height(16.dp))

        PokemonText(
            attrs = AttrsPokemonText(
                text = name,
                color = Color.Black,
                fontSize = 28.sp,
                attrsFontFamily = AttrsPokemonTextFontFamily(
                    resId = R.font.lemonmilk_bold
                )
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        PokemonText(
            attrs = AttrsPokemonText(
                text = "Abilities: " + abilities.joinToString { it.ability.name },
                color = Color.Black,
                fontSize = 20.sp,
                attrsFontFamily = AttrsPokemonTextFontFamily(
                    resId = R.font.lemonmilk_medium
                )
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        PokemonTypesList(
            types = types,
            backgroundColor = backgroundColor
        )
    }
}
