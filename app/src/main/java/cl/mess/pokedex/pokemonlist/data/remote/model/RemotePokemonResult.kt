package cl.mess.pokedex.pokemonlist.data.remote.model

import cl.mess.pokedex.pokemonlist.data.remote.model.Constants.NAME
import cl.mess.pokedex.pokemonlist.data.remote.model.Constants.URL
import com.google.gson.annotations.SerializedName

data class RemotePokemonResult(
    @SerializedName(NAME) val name: String?,
    @SerializedName(URL) val url: String?
)
