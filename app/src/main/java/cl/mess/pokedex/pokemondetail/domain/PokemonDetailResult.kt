package cl.mess.pokedex.pokemondetail.domain

import cl.mess.pokedex.pokemondetail.domain.model.PokemonDetail

sealed class PokemonDetailResult {
    data class ErrorException(val exception: Exception): PokemonDetailResult()
    data class Success(val pokemonDetail: PokemonDetail): PokemonDetailResult()
}
