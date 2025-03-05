package cl.mess.pokedex.ui.composables.pokemonerror

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.pokedex.R
import cl.mess.pokedex.ui.composables.AttrsPokemonText
import cl.mess.pokedex.ui.composables.AttrsPokemonTextFontFamily
import cl.mess.pokedex.ui.composables.PokemonText

@Composable
fun PokemonErrorContent(
    onRetry: () -> Unit,
    paddingValues: PaddingValues,
    message: String,
    painter: Painter
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            Icon(
                tint = Color.Red,
                modifier = Modifier.size(size = 64.dp),
                painter = painter,
                contentDescription = ""
            )
            PokemonText(
                attrs = AttrsPokemonText(
                    text = message,
                    fontSize = 16.sp,
                    attrsFontFamily = AttrsPokemonTextFontFamily(
                        resId = R.font.lemonmilk_medium,
                        weight = FontWeight.Bold
                    ),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Red
                )
            )
            Button(
                onClick = onRetry,
                colors =  ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                PokemonText(
                    attrs = AttrsPokemonText(
                        text = "Retry",
                        fontSize = 16.sp,
                        attrsFontFamily = AttrsPokemonTextFontFamily(
                            resId = R.font.lemonmilk_medium,
                            weight = FontWeight.Bold
                        ),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                )
            }
        }
    }
}
