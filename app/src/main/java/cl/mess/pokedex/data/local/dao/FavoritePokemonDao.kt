package cl.mess.pokedex.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import cl.mess.pokedex.data.local.entities.FavoritePokemon

@Dao
interface FavoritePokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(pokemon: FavoritePokemon)

    @Delete
    suspend fun removeFavorite(pokemon: FavoritePokemon)

    @Query("SELECT * FROM favorite_pokemon")
    suspend fun getAllFavorites(): List<FavoritePokemon>

    @Query("SELECT * FROM favorite_pokemon WHERE name = :pokemonName LIMIT 1")
    suspend fun isPokemonFavorite(pokemonName: String): FavoritePokemon?
}
