package cl.mess.pokedex.pokemonlist.data.local

import cl.mess.pokedex.pokemonlist.data.local.dao.SearchHistoryDao
import cl.mess.pokedex.pokemonlist.data.local.entities.SearchHistory
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dao: SearchHistoryDao
) : SearchRepository {

    override suspend fun saveSearchTerm(term: String) {
        dao.insert(searchHistory = SearchHistory(searchTerm = term))
    }

    override suspend fun searchTerms(term: String): List<SearchHistory> {
        return dao.search(term = term)
    }

    override suspend fun getAllSearchHistory(): List<SearchHistory> {
        return dao.getAllSearchHistory()
    }
}
