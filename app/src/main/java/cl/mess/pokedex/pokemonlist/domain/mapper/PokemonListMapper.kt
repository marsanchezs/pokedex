package cl.mess.pokedex.pokemonlist.domain.mapper

import cl.mess.pokedex.pokemonlist.data.remote.model.RemotePokemonResult
import cl.mess.pokedex.pokemonlist.domain.model.PokemonResult
import cl.mess.pokedex.utils.buildImageUrl
import javax.inject.Inject

class PokemonListMapper @Inject constructor(){
    fun toPokemonResult(remotePokemon: RemotePokemonResult): PokemonResult {
        return PokemonResult(
            name = remotePokemon.name ?: "Unknown",
            imageUrl = buildImageUrl(url = remotePokemon.url)
        )
    }
}
