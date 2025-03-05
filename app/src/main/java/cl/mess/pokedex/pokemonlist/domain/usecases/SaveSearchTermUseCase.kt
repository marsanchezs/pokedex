package cl.mess.pokedex.pokemonlist.domain.usecases

import cl.mess.pokedex.pokemonlist.data.local.SearchRepository
import javax.inject.Inject

class SaveSearchTermUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend fun execute(term: String) {
        repository.saveSearchTerm(term = term)
    }
}
