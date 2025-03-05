package cl.mess.pokedex.pokemondetail.data.remote

import cl.mess.pokedex.pokemondetail.data.remote.retrofit.PokemonDetailWebService
import cl.mess.pokedex.pokemondetail.data.source.PokemonDetailRemote
import javax.inject.Inject

class PokemonDetailRemoteImpl @Inject constructor(
    private val api: PokemonDetailWebService
) : PokemonDetailRemote {
    override suspend fun getPokemonDetail(pokemonName: String) =
        api.getPokemonDetail(pokemonName = pokemonName)
}
