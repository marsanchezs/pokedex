package cl.mess.pokedex.pokemonlist.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cl.mess.pokedex.pokemonlist.data.remote.model.RemotePokemonResult
import cl.mess.pokedex.pokemonlist.data.remote.retrofit.PokemonListWebService
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val api: PokemonListWebService
) : PagingSource<Int, RemotePokemonResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RemotePokemonResult> {
        return try {
            val currentPage = params.key ?: 0
            val response = api.getPokemonList(
                limit = 20,
                offset = currentPage * 20
            )
            val pokemonList = response.results?.filterNotNull() ?: emptyList()

            LoadResult.Page(
                data = pokemonList,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (response.results?.isEmpty() == true) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RemotePokemonResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
