package cl.mess.pokedex.pokemondetail.domain.model

data class PokemonDetail(
    val name: String,
    val abilities: List<AbilityWrapper>,
    val types: List<TypeWrapper>,
    val sprites: SpriteImages
)
