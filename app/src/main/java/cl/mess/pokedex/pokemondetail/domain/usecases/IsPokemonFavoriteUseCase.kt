package cl.mess.pokedex.pokemondetail.domain.usecases

import cl.mess.pokedex.data.repository.FavoritePokemonRepository
import javax.inject.Inject

class IsPokemonFavoriteUseCase @Inject constructor(
    private val repository: FavoritePokemonRepository
) {
    suspend fun execute(pokemonName: String): Boolean {
        return repository.isPokemonFavorite(pokemonName)
    }
}
