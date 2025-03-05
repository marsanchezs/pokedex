package cl.mess.pokedex.pokemonlist.domain.usecases

import cl.mess.pokedex.pokemonlist.data.local.SearchRepository
import cl.mess.pokedex.pokemonlist.data.local.entities.SearchHistory
import javax.inject.Inject

class SearchTermsUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend fun execute(term: String): List<SearchHistory> {
        return repository.searchTerms(term = term)
    }

    suspend fun getAll(): List<SearchHistory> {
        return repository.getAllSearchHistory()
    }
}
