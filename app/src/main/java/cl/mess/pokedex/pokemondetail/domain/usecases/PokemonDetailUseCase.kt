package cl.mess.pokedex.pokemondetail.domain.usecases

import cl.mess.pokedex.pokemondetail.data.PokemonDetailRepository
import cl.mess.pokedex.pokemondetail.domain.PokemonDetailResult
import cl.mess.pokedex.pokemondetail.domain.mapper.PokemonDetailMapper
import javax.inject.Inject

class PokemonDetailUseCase @Inject constructor(
    private val repository: PokemonDetailRepository,
    private val mapper: PokemonDetailMapper
) {
    suspend fun getPokemonDetail(pokemonName: String): PokemonDetailResult {
        return try {
            val remote = repository.getPokemonDetail(pokemonName = pokemonName)
            val pokemonDetail = with(receiver = mapper) { remote.toPokemonDetail() }
            PokemonDetailResult.Success(pokemonDetail = pokemonDetail)
        } catch (exception: Exception) {
            PokemonDetailResult.ErrorException(exception = exception)
        }
    }
}
