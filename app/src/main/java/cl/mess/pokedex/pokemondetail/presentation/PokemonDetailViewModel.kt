package cl.mess.pokedex.pokemondetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.mess.pokedex.pokemondetail.domain.PokemonDetailResult
import cl.mess.pokedex.pokemondetail.domain.model.PokemonDetail
import cl.mess.pokedex.pokemondetail.domain.model.SpriteImages
import cl.mess.pokedex.pokemondetail.domain.usecases.AddFavoritePokemonUseCase
import cl.mess.pokedex.pokemondetail.domain.usecases.IsPokemonFavoriteUseCase
import cl.mess.pokedex.pokemondetail.domain.usecases.PokemonDetailUseCase
import cl.mess.pokedex.pokemondetail.domain.usecases.RemoveFavoritePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val useCase: PokemonDetailUseCase,
    private val addUseCase: AddFavoritePokemonUseCase,
    private val removeUseCase: RemoveFavoritePokemonUseCase,
    private val isPokemonFavoriteUseCase: IsPokemonFavoriteUseCase
) : ViewModel() {

    private val viewStateMutableStateFlow = MutableStateFlow<PokemonDetailUiState>(PokemonDetailUiState.LoadingUiState)
    val viewState: StateFlow<PokemonDetailUiState> get() = viewStateMutableStateFlow

    private val emptyPokemonDetail = PokemonDetail(
        name = "",
        abilities = emptyList(),
        types = emptyList(),
        sprites = SpriteImages(
            frontDefault = "",
            frontShiny = "",
            backDefault = "",
            backShiny = ""
        )
    )
    private val _pokemonDetail = MutableStateFlow(emptyPokemonDetail)
    val pokemonDetail: StateFlow<PokemonDetail> get() = _pokemonDetail

    private val _shareData = MutableStateFlow(Pair("", ""))
    val shareData: StateFlow<Pair<String, String>> = _shareData

    private val _isPokemonFavorite = MutableStateFlow(value = false)
    val isPokemonFavorite: StateFlow<Boolean> get() = _isPokemonFavorite

    fun getPokemonDetail(pokemonName: String) {
        viewModelScope.launch {
            viewStateMutableStateFlow.value = PokemonDetailUiState.LoadingUiState
            when (val result = useCase.getPokemonDetail(pokemonName = pokemonName)) {
                is PokemonDetailResult.ErrorException -> {
                    viewStateMutableStateFlow.value = PokemonDetailUiState.ErrorUiState(message = result.exception.message.toString())
                }
                is PokemonDetailResult.Success -> {
                    _pokemonDetail.value = result.pokemonDetail
                    viewStateMutableStateFlow.value = PokemonDetailUiState.SuccessUiState(pokemonDetail = result.pokemonDetail)
                }
            }
        }
    }

    fun setShareData(pokemonDetail: PokemonDetail) {
        _shareData.value = Pair(
            "Check out this Pokémon: ${pokemonDetail.name}",
            pokemonDetail.sprites.frontDefault
        )
    }

    fun checkIfPokemonIsFavorite(pokemonName: String) {
        viewModelScope.launch {
            val exists = isPokemonFavoriteUseCase.execute(pokemonName)
            _isPokemonFavorite.value = exists
        }
    }

    fun toggleFavorite(pokemon: PokemonDetail) {
        viewModelScope.launch {
            val isFavorite = _isPokemonFavorite.value
            if (isFavorite) {
                removeUseCase(pokemon)
            } else {
                addUseCase(pokemon)
            }
            _isPokemonFavorite.value = !isFavorite
        }
    }
}
