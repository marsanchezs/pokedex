package cl.mess.pokedex.pokemonlist.domain.usecases

import androidx.paging.PagingData
import androidx.paging.map
import cl.mess.pokedex.pokemonlist.data.remote.PokemonListRepository
import cl.mess.pokedex.pokemonlist.domain.mapper.PokemonListMapper
import cl.mess.pokedex.pokemonlist.domain.model.PokemonResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonListRepository,
    private val mapper: PokemonListMapper
) {
    fun invoke(): Flow<PagingData<PokemonResult>> {
        return repository.getPokemonList()
            .map { pagingData ->
                pagingData.map { remotePokemon ->
                    mapper.toPokemonResult(remotePokemon = remotePokemon)
                }
            }
    }
}
