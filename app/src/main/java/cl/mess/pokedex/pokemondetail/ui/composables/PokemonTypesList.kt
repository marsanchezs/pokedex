package cl.mess.pokedex.pokemondetail.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.pokedex.R
import cl.mess.pokedex.pokemondetail.domain.model.TypeWrapper
import cl.mess.pokedex.ui.composables.AttrsPokemonText
import cl.mess.pokedex.ui.composables.AttrsPokemonTextFontFamily
import cl.mess.pokedex.ui.composables.PokemonText

@Composable
fun PokemonTypesList(
    types: List<TypeWrapper>,
    backgroundColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Espaciado entre los tipos
    ) {
        types.forEach { typeWrapper ->
            PokemonText(
                attrs = AttrsPokemonText(
                    text = typeWrapper.type.name,
                    color = Color.White, // Color del texto
                    fontSize = 20.sp, // Tamaño de fuente
                    attrsFontFamily = AttrsPokemonTextFontFamily(
                        resId = R.font.lemonmilk_medium
                    ),
                    modifier = Modifier
                        .background(
                            color = backgroundColor, // Color de fondo según el tipo
                            shape = RoundedCornerShape(16.dp) // Bordes redondeados
                        )
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
            )
        }
    }
}
