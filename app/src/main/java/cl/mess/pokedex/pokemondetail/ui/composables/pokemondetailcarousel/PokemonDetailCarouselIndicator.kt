package cl.mess.pokedex.pokemondetail.ui.composables.pokemondetailcarousel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PokemonDetailCarouselIndicator(currentPage: Int){
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(times = 4) { index ->
            val color = if (currentPage == index) Color.Gray else Color.LightGray
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color)
                    .padding(2.dp)
            )
            if (index < 3) {
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}
