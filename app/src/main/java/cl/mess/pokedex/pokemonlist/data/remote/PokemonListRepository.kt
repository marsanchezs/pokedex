package cl.mess.pokedex.pokemonlist.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import cl.mess.pokedex.pokemonlist.data.remote.model.RemotePokemonResult
import cl.mess.pokedex.pokemonlist.data.remote.retrofit.PokemonListWebService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonListRepository @Inject constructor(private val api: PokemonListWebService) {

    fun getPokemonList(): Flow<PagingData<RemotePokemonResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonPagingSource(api = api) }
        ).flow
    }
}
