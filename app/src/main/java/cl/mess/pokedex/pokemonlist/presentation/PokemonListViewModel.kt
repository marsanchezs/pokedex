package cl.mess.pokedex.pokemonlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cl.mess.pokedex.data.local.entities.FavoritePokemon
import cl.mess.pokedex.data.remote.NetworkMonitor
import cl.mess.pokedex.pokemonlist.domain.usecases.GetFavoritePokemonsUseCase
import cl.mess.pokedex.pokemonlist.data.local.entities.SearchHistory
import cl.mess.pokedex.pokemonlist.domain.usecases.GetPokemonListUseCase
import cl.mess.pokedex.pokemonlist.domain.usecases.SaveSearchTermUseCase
import cl.mess.pokedex.pokemonlist.domain.usecases.SearchTermsUseCase
import cl.mess.pokedex.pokemonlist.domain.model.PokemonResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    useCase: GetPokemonListUseCase,
    private val getFavoritesUseCase: GetFavoritePokemonsUseCase,
    private val saveTermUseCase: SaveSearchTermUseCase,
    private val searchTermsUseCase: SearchTermsUseCase,
    networkMonitor: NetworkMonitor
) : ViewModel() {

    val pokemonList: Flow<PagingData<PokemonResult>> = useCase.invoke()
        .cachedIn(viewModelScope)

    private val _favorites = MutableStateFlow<List<FavoritePokemon>>(emptyList())
    val favorites: StateFlow<List<FavoritePokemon>> get() = _favorites

    private val _searchHistory = MutableStateFlow<List<SearchHistory>>(emptyList())
    val searchHistory: StateFlow<List<SearchHistory>> get() = _searchHistory

    val isConnected = networkMonitor.isConnected.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = false
    )

    init {
        refreshFavorites()
        loadAllSearchHistory()
    }

    fun refreshFavorites() {
        viewModelScope.launch {
            _favorites.value = getFavoritesUseCase()
        }
    }

    fun saveSearchTerm(term: String) {
        viewModelScope.launch {
            saveTermUseCase.execute(term)
            loadSearchHistory(term)
        }
    }

    private fun loadSearchHistory(query: String) {
        viewModelScope.launch {
            _searchHistory.value = if (query.isNotEmpty()) {
                searchTermsUseCase.execute(query)
            } else {
                emptyList()
            }
        }
    }

    fun loadAllSearchHistory() {
        viewModelScope.launch {
            _searchHistory.value = searchTermsUseCase.getAll()
        }
    }
}
