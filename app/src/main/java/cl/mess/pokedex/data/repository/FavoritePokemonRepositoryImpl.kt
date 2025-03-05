package cl.mess.pokedex.data.repository

import cl.mess.pokedex.data.local.dao.FavoritePokemonDao
import cl.mess.pokedex.data.local.entities.FavoritePokemon
import javax.inject.Inject

class FavoritePokemonRepositoryImpl @Inject constructor(
    private val dao: FavoritePokemonDao
) : FavoritePokemonRepository {

    override suspend fun addFavorite(pokemon: FavoritePokemon) {
        dao.addFavorite(pokemon)
    }

    override suspend fun removeFavorite(pokemon: FavoritePokemon) {
        dao.removeFavorite(pokemon)
    }

    override suspend fun getAllFavorites(): List<FavoritePokemon> {
        return dao.getAllFavorites()
    }

    override suspend fun isPokemonFavorite(pokemonName: String): Boolean {
        val favoritePokemon = dao.isPokemonFavorite(pokemonName)
        return favoritePokemon != null
    }
}
