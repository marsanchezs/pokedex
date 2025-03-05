package cl.mess.pokedex.pokemondetail.data.remote.model

import cl.mess.pokedex.pokemondetail.data.remote.model.Constants.ABILITIES
import cl.mess.pokedex.pokemondetail.data.remote.model.Constants.NAME
import cl.mess.pokedex.pokemondetail.data.remote.model.Constants.SPRITES
import cl.mess.pokedex.pokemondetail.data.remote.model.Constants.TYPES
import com.google.gson.annotations.SerializedName

data class RemotePokemonDetail(
    @SerializedName(NAME) val name: String?,
    @SerializedName(ABILITIES) val abilities: List<RemoteAbilityWrapper?>?,
    @SerializedName(TYPES) val types: List<RemoteTypeWrapper?>?,
    @SerializedName(SPRITES) val sprites: RemoteSpriteImages?
)
