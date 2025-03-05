package cl.mess.pokedex.pokemondetail.ui.composables.pokemondetailcarousel

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.mess.pokedex.pokemondetail.domain.model.SpriteImages
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PokemonDetailCarousel(
    pagerState: PagerState,
    sprites: SpriteImages
){
    PokemonDetailCarouselPager(
        pagerState = pagerState,
        sprites = sprites
    )

    Spacer(modifier = Modifier.height(16.dp))

    PokemonDetailCarouselIndicator(currentPage = pagerState.currentPage)
}
