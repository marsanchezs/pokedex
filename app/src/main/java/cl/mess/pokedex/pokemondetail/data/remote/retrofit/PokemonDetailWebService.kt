package cl.mess.pokedex.pokemondetail.data.remote.retrofit

import cl.mess.pokedex.pokemondetail.data.remote.model.Constants.NAME
import cl.mess.pokedex.pokemondetail.data.remote.model.RemotePokemonDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDetailWebService {
    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path(NAME) pokemonName: String): RemotePokemonDetail
}
