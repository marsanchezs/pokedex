package cl.mess.pokedex.pokemonlist.data.remote.model

import cl.mess.pokedex.pokemonlist.data.remote.model.Constants.COUNT
import cl.mess.pokedex.pokemonlist.data.remote.model.Constants.NEXT
import cl.mess.pokedex.pokemonlist.data.remote.model.Constants.PREVIOUS
import cl.mess.pokedex.pokemonlist.data.remote.model.Constants.RESULTS
import com.google.gson.annotations.SerializedName

data class RemotePokemonList(
    @SerializedName(COUNT) val count: Int?,
    @SerializedName(NEXT) val next: String?,
    @SerializedName(PREVIOUS) val previous: String?,
    @SerializedName(RESULTS) val results: List<RemotePokemonResult?>?
)
