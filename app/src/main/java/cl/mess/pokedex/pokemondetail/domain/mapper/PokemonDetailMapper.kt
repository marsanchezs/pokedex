package cl.mess.pokedex.pokemondetail.domain.mapper

import cl.mess.pokedex.data.local.entities.FavoritePokemon
import cl.mess.pokedex.pokemondetail.data.remote.model.RemoteAbility
import cl.mess.pokedex.pokemondetail.data.remote.model.RemoteAbilityWrapper
import cl.mess.pokedex.pokemondetail.data.remote.model.RemotePokemonDetail
import cl.mess.pokedex.pokemondetail.data.remote.model.RemoteSpriteImages
import cl.mess.pokedex.pokemondetail.data.remote.model.RemoteType
import cl.mess.pokedex.pokemondetail.data.remote.model.RemoteTypeWrapper
import cl.mess.pokedex.pokemondetail.domain.model.Ability
import cl.mess.pokedex.pokemondetail.domain.model.AbilityWrapper
import cl.mess.pokedex.pokemondetail.domain.model.PokemonDetail
import cl.mess.pokedex.pokemondetail.domain.model.SpriteImages
import cl.mess.pokedex.pokemondetail.domain.model.Type
import cl.mess.pokedex.pokemondetail.domain.model.TypeWrapper
import javax.inject.Inject

class PokemonDetailMapper @Inject constructor() {

    fun RemotePokemonDetail.toPokemonDetail() = PokemonDetail(
        name = name.orEmpty(),
        abilities = abilities.orEmpty().mapNotNull { it?.toAbilityWrapper() },
        types = types.orEmpty().mapNotNull { it?.toTypeWrapper() },
        sprites = sprites?.toSprites() ?: SpriteImages(
            frontDefault = "",
            frontShiny = "",
            backDefault = "",
            backShiny = ""
        )
    )

    private fun RemoteAbilityWrapper.toAbilityWrapper() = AbilityWrapper(
        ability = ability?.toAbility() ?: Ability(name = "")
    )

    private fun RemoteAbility.toAbility() = Ability(
        name = name.orEmpty()
    )

    private fun RemoteTypeWrapper.toTypeWrapper() = TypeWrapper(
        type = type?.toType() ?: Type(name = "")
    )

    private fun RemoteType.toType() = Type(
        name = name.orEmpty()
    )

    private fun RemoteSpriteImages.toSprites() = SpriteImages(
        frontDefault = frontDefault.orEmpty(),
        frontShiny = frontShiny.orEmpty(),
        backDefault = backDefault.orEmpty(),
        backShiny = backShiny.orEmpty()
    )

    fun PokemonDetail.toFavoritePokemon() = FavoritePokemon(
        name = name,
        frontImageUrl = sprites.frontDefault
    )
}
