package cl.mess.pokedex.data.repository

import cl.mess.pokedex.data.local.entities.FavoritePokemon

interface FavoritePokemonRepository {
    suspend fun addFavorite(pokemon: FavoritePokemon)
    suspend fun removeFavorite(pokemon: FavoritePokemon)
    suspend fun getAllFavorites(): List<FavoritePokemon>
    suspend fun isPokemonFavorite(pokemonName: String): Boolean
}
