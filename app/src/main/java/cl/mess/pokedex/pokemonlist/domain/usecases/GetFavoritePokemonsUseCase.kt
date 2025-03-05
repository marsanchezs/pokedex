package cl.mess.pokedex.pokemonlist.domain.usecases

import cl.mess.pokedex.data.local.entities.FavoritePokemon
import cl.mess.pokedex.data.repository.FavoritePokemonRepository
import javax.inject.Inject

class GetFavoritePokemonsUseCase @Inject constructor(
    private val repository: FavoritePokemonRepository
) {
    suspend operator fun invoke(): List<FavoritePokemon> {
        return repository.getAllFavorites()
    }
}
