package cl.mess.pokedex.pokemondetail.domain.usecases

import cl.mess.pokedex.data.repository.FavoritePokemonRepository
import cl.mess.pokedex.pokemondetail.domain.mapper.PokemonDetailMapper
import cl.mess.pokedex.pokemondetail.domain.model.PokemonDetail
import javax.inject.Inject

class AddFavoritePokemonUseCase @Inject constructor(
    private val repository: FavoritePokemonRepository,
    private val mapper: PokemonDetailMapper
) {
    suspend operator fun invoke(pokemon: PokemonDetail) {
        val favoritePokemon = with(receiver = mapper) { pokemon.toFavoritePokemon() }
        repository.addFavorite(pokemon = favoritePokemon)
    }
}
