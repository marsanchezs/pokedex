package cl.mess.pokedex.pokemonlist.data.remote.retrofit

import cl.mess.pokedex.pokemonlist.data.remote.model.Constants.LIMIT
import cl.mess.pokedex.pokemonlist.data.remote.model.Constants.OFFSET
import cl.mess.pokedex.pokemonlist.data.remote.model.RemotePokemonList
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonListWebService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query(OFFSET) offset: Int,
        @Query(LIMIT) limit: Int
    ): RemotePokemonList
}
