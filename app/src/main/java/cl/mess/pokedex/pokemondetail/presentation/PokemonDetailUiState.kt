package cl.mess.pokedex.pokemondetail.presentation

import cl.mess.pokedex.pokemondetail.domain.model.PokemonDetail

open class PokemonDetailUiState {
    object LoadingUiState : PokemonDetailUiState()
    data class SuccessUiState(val pokemonDetail: PokemonDetail) : PokemonDetailUiState()
    data class ErrorUiState(val message: String) : PokemonDetailUiState()
}
