package cl.mess.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.mess.pokedex.data.local.dao.FavoritePokemonDao
import cl.mess.pokedex.data.local.entities.FavoritePokemon
import cl.mess.pokedex.pokemonlist.data.local.dao.SearchHistoryDao
import cl.mess.pokedex.pokemonlist.data.local.entities.SearchHistory

@Database(entities = [FavoritePokemon::class, SearchHistory::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun favoritePokemonDao(): FavoritePokemonDao
    abstract fun searchHistoryDao(): SearchHistoryDao
}
