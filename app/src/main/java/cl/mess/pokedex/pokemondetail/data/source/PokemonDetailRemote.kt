package cl.mess.pokedex.pokemondetail.data.source

import cl.mess.pokedex.pokemondetail.data.remote.model.RemotePokemonDetail

interface PokemonDetailRemote {
    suspend fun getPokemonDetail(pokemonName: String): RemotePokemonDetail
}
