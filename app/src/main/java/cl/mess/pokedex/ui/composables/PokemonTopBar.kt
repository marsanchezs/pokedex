package cl.mess.pokedex.ui.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cl.mess.pokedex.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTopBar(
    onBack: () -> Unit,
    text: String = "",
    colorText: Color = Color.White,
    backgroundColor: Color = Color.Gray,
    actions: @Composable() (RowScope.() -> Unit) = {}
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor),
        title = {
            PokemonText(
                attrs = AttrsPokemonText(
                    text = text,
                    fontSize = 20.sp,
                    color = colorText,
                    attrsFontFamily = AttrsPokemonTextFontFamily(
                        resId = R.font.lemonmilk_medium,
                        weight = FontWeight.Bold
                    ),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = "Back"
                )
            }
        },
        actions = actions
    )
}
