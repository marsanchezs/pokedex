package cl.mess.pokedex.utils

import androidx.compose.ui.graphics.Color
import cl.mess.pokedex.pokemondetail.domain.model.PokemonDetail
import cl.mess.pokedex.pokemondetail.domain.model.TypeWrapper
import java.util.Locale

fun buildImageUrl(url: String?): String {
    val id = url?.trimEnd('/')?.split("/")?.lastOrNull() ?: "0"
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}

val TYPE_COLORS = mapOf(
    "fire" to Color(0xFFFF5722),  // Rojo para Fuego
    "water" to Color(0xFF2196F3),  // Azul para Agua
    "grass" to Color(0xFF4CAF50),  // Verde para Planta
    "electric" to Color(0xFFFFEB3B),  // Amarillo para Eléctrico
    "bug" to Color(0xFF8BC34A),  // Verde para Bicho
    "normal" to Color(0xFF9E9E9E),  // Gris para Normal
    "flying" to Color(0xFF03A9F4),  // Celeste para Volador
    "poison" to Color(0xFF9C27B0),  // Morado para Veneno
    "fairy" to Color(0xFFFFC0CB),  // Rosa para Hada
    "psychic" to Color(0xFF9C27B0),  // Morado para Psíquico
    "ground" to Color(0xFF795548),  // Marrón para Tierra
    "rock" to Color(0xFF607D8B),  // Gris para Roca
    "ice" to Color(0xFF00BCD4),  // Aqua para Hielo
    "dragon" to Color(0xFF673AB7),  // Violeta para Dragón
    "dark" to Color(0xFF212121),  // Negro para Siniestro
    "steel" to Color(0xFF607D8B)  // Gris para Acero
)

fun TypeWrapper.getColorForType(): Color {
    return TYPE_COLORS[type.name.lowercase(Locale.ROOT)] ?: Color.Gray  // Color por defecto en caso de que no esté en el mapa
}

fun getPokemonTypeColor(pokemonDetail: PokemonDetail): Color {
    return pokemonDetail.types.firstOrNull()?.getColorForType() ?: Color.Gray  // Obtiene el color del primer tipo, o gris si no tiene tipos
}

