package cl.mess.pokedex.pokemonlist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.mess.pokedex.pokemonlist.data.local.entities.SearchHistory

@Dao
interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchHistory: SearchHistory)

    @Query("SELECT * FROM search_history WHERE searchTerm LIKE :term ORDER BY id DESC")
    suspend fun search(term: String): List<SearchHistory>

    @Query("SELECT * FROM search_history ORDER BY id DESC")
    suspend fun getAllSearchHistory(): List<SearchHistory>

}
