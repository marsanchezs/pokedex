package cl.mess.pokedex.di

import android.content.Context
import androidx.room.Room
import cl.mess.pokedex.data.local.PokemonDatabase
import cl.mess.pokedex.data.local.dao.FavoritePokemonDao
import cl.mess.pokedex.data.repository.FavoritePokemonRepository
import cl.mess.pokedex.data.repository.FavoritePokemonRepositoryImpl
import cl.mess.pokedex.pokemonlist.data.local.SearchRepository
import cl.mess.pokedex.pokemonlist.data.local.SearchRepositoryImpl
import cl.mess.pokedex.pokemonlist.data.local.dao.SearchHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val POKEMON_DATABASE_NAME = "pokemon_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PokemonDatabase::class.java, POKEMON_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideFavoritePokemonDao(db: PokemonDatabase) = db.favoritePokemonDao()

    @Provides
    fun provideFavoritePokemonRepository(dao: FavoritePokemonDao): FavoritePokemonRepository {
        return FavoritePokemonRepositoryImpl(dao = dao)
    }

    @Singleton
    @Provides
    fun provideSearchHistoryDao(db: PokemonDatabase) = db.searchHistoryDao()

    @Provides
    fun provideSearchRepository(dao: SearchHistoryDao): SearchRepository {
        return SearchRepositoryImpl(dao = dao)
    }
}
