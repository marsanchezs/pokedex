package cl.mess.pokedex.pokemondetail.data.remote.model

import cl.mess.pokedex.pokemondetail.data.remote.model.Constants.BACK_DEFAULT
import cl.mess.pokedex.pokemondetail.data.remote.model.Constants.BACK_SHINY
import cl.mess.pokedex.pokemondetail.data.remote.model.Constants.FRONT_DEFAULT
import cl.mess.pokedex.pokemondetail.data.remote.model.Constants.FRONT_SHINY
import com.google.gson.annotations.SerializedName

data class RemoteSpriteImages(
    @SerializedName(FRONT_DEFAULT) val frontDefault: String?,
    @SerializedName(FRONT_SHINY) val frontShiny: String?,
    @SerializedName(BACK_DEFAULT) val backDefault: String?,
    @SerializedName(BACK_SHINY) val backShiny: String?
)
