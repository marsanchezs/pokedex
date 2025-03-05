package cl.mess.pokedex.pokemonlist.data.remote

import androidx.paging.Pager
import androidx.paging.PagingData
import cl.mess.pokedex.pokemonlist.data.remote.model.RemotePokemonResult
import cl.mess.pokedex.pokemonlist.data.remote.retrofit.PokemonListWebService
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class PokemonListRepositoryTest {

    private lateinit var repository: PokemonListRepository
    private val api: PokemonListWebService = mockk()

    @Before
    fun setUp() {
        repository = PokemonListRepository(api)
    }

    @Test
    fun `given successful API response, when getPokemonList is called, then return paging data`() = runTest {
        // Given
        val fakeData = listOf(RemotePokemonResult(name = "Pikachu", url = "url"))
        val fakePagingData = PagingData.from(fakeData)

        val flow = flowOf(fakePagingData)

        mockkConstructor(Pager::class)
        every { anyConstructed<Pager<Int, RemotePokemonResult>>().flow } returns flow

        // When
        val result = repository.getPokemonList()

        // Then
        result.collectLatest { pagingData ->
            assertEquals(fakePagingData, pagingData)
        }
    }
}


