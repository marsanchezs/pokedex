package cl.mess.pokedex.pokemondetail.data.remote.model

import cl.mess.pokedex.pokemondetail.data.remote.model.Constants.NAME
import cl.mess.pokedex.pokemondetail.data.remote.model.Constants.TYPE
import com.google.gson.annotations.SerializedName

data class RemoteTypeWrapper(@SerializedName(TYPE) val type: RemoteType?)

data class RemoteType(@SerializedName(NAME) val name: String?)
