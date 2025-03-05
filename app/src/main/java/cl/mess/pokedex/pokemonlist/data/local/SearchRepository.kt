package cl.mess.pokedex.pokemonlist.data.local

import cl.mess.pokedex.pokemonlist.data.local.entities.SearchHistory

interface SearchRepository {
    suspend fun saveSearchTerm(term: String)
    suspend fun searchTerms(term: String): List<SearchHistory>
    suspend fun getAllSearchHistory(): List<SearchHistory>
}
