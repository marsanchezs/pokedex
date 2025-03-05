package cl.mess.pokedex.pokemondetail.data.remote.model

import cl.mess.pokedex.pokemondetail.data.remote.model.Constants.ABILITY
import cl.mess.pokedex.pokemondetail.data.remote.model.Constants.NAME
import com.google.gson.annotations.SerializedName

data class RemoteAbilityWrapper(@SerializedName(ABILITY) val ability: RemoteAbility?)

data class RemoteAbility(@SerializedName(NAME) val name: String?)
