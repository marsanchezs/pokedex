package cl.mess.pokedex.pokemondetail.data

import cl.mess.pokedex.pokemondetail.data.remote.model.RemotePokemonDetail
import cl.mess.pokedex.pokemondetail.data.remote.model.RemoteSpriteImages
import cl.mess.pokedex.pokemondetail.data.source.PokemonDetailRemote
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class PokemonDetailRepositoryTest {

    private lateinit var repository: PokemonDetailRepository
    private val remote: PokemonDetailRemote = mockk()

    @Before
    fun setUp() {
        repository = PokemonDetailRepository(remote)
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
        coEvery { remote.getPokemonDetail(pokemonName) } returns expectedDetail

        // When
        val result = repository.getPokemonDetail(pokemonName)

        // Then
        assertEquals(expectedDetail, result)
        coVerify(exactly = 1) { remote.getPokemonDetail(pokemonName) }
    }
}