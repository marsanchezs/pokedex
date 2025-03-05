package cl.mess.pokedex.navigation

import cl.mess.pokedex.navigation.Constants.POKEMON_DETAIL
import cl.mess.pokedex.navigation.Constants.POKEMON_LIST

sealed class PokemonRoutes(val path: String) {
    data object PokemonList : PokemonRoutes(path = POKEMON_LIST)
    data object PokemonDetail : PokemonRoutes(path = POKEMON_DETAIL)
}
