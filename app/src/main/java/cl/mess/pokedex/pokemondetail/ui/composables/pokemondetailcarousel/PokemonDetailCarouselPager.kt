package cl.mess.pokedex.pokemondetail.ui.composables.pokemondetailcarousel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cl.mess.pokedex.pokemondetail.domain.model.SpriteImages
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PokemonDetailCarouselPager(
    pagerState: PagerState,
    sprites: SpriteImages
){
    HorizontalPager(
        count = 4,
        state = pagerState
    ) { page ->
        val sprite = when (page) {
            0 -> sprites.frontDefault
            1 -> sprites.frontShiny
            2 -> sprites.backDefault
            3 -> sprites.backShiny
            else -> sprites.frontDefault
        }
        Image(
            painter = rememberAsyncImagePainter(sprite),
            contentDescription = "Pokemon sprite",
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)  // Ajusta el tamaño de la imagen
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }
}
