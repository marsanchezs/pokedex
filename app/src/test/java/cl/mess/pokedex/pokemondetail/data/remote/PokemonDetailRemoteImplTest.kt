package cl.mess.pokedex.pokemondetail.data.remote

import cl.mess.pokedex.pokemondetail.data.remote.model.RemotePokemonDetail
import cl.mess.pokedex.pokemondetail.data.remote.model.RemoteSpriteImages
import cl.mess.pokedex.pokemondetail.data.remote.retrofit.PokemonDetailWebService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class PokemonDetailRemoteImplTest {

    private lateinit var remote: PokemonDetailRemoteImpl
    private val api: PokemonDetailWebService = mockk()

    @Before
    fun setUp() {
        remote = PokemonDetailRemoteImpl(api)
    }

    @Test
    fun `given valid pokemon name, when getPokemonDetail is called, then return pokemon detail`() = runTest {
        // Given
        val pokemonName = "pikachu"
        val expectedDetail = RemotePokemonDetail(
            name = "Pikachu",
            abilities = emptyList(),
            types = emptyList(),
            sprites = RemoteSpriteImages(
                frontDefault = "https://example.com/pikachu.png",
                frontShiny = "https://example.com/pikachu.png",
                backDefault = "https://example.com/pikachu.png",
                backShiny = "https://example.com/pikachu.png",
            )
        )

        coEvery { api.getPokemonDetail(pokemonName) } returns expectedDetail

        // When
        val result = remote.getPokemonDetail(pokemonName)

        // Then
        assertEquals(expectedDetail, result)
        coVerify(exactly = 1) { api.getPokemonDetail(pokemonName) }
    }
}
