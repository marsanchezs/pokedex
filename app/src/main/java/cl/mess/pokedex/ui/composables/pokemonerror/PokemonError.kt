package cl.mess.pokedex.ui.composables.pokemonerror

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import cl.mess.pokedex.R
import cl.mess.pokedex.ui.composables.PokemonTopBar

@Composable
fun PokemonError(
    message: String = "An error has occurred",
    painter: Painter = painterResource(id = R.drawable.baseline_wifi_tethering_error_24),
    onBack: () -> Unit = {},
    onRetry: () -> Unit = {},
) {
    Scaffold(
        topBar = {
           PokemonTopBar(
                onBack = onBack,
                text = "Error Screen",
                backgroundColor = Color.Red
            )
        }
    ) { paddingValues ->
        PokemonErrorContent(
            onRetry = onRetry,
            paddingValues = paddingValues,
            message = message,
            painter = painter
        )
    }
}
