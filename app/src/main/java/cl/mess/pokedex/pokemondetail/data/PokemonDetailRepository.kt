package cl.mess.pokedex.pokemondetail.data

import cl.mess.pokedex.pokemondetail.data.source.PokemonDetailRemote
import javax.inject.Inject

class PokemonDetailRepository @Inject constructor(
    private val remote: PokemonDetailRemote
) {
    suspend fun getPokemonDetail(pokemonName: String) =
        remote.getPokemonDetail(pokemonName = pokemonName)
}
