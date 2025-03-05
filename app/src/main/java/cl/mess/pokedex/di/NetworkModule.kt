package cl.mess.pokedex.di

import android.content.Context
import cl.mess.pokedex.data.remote.NetworkMonitor
import cl.mess.pokedex.pokemondetail.data.remote.PokemonDetailRemoteImpl
import cl.mess.pokedex.pokemondetail.data.remote.retrofit.PokemonDetailWebService
import cl.mess.pokedex.pokemondetail.data.source.PokemonDetailRemote
import cl.mess.pokedex.pokemonlist.data.remote.PokemonListRepository
import cl.mess.pokedex.pokemonlist.data.remote.retrofit.PokemonListWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonListWebService(retrofit: Retrofit): PokemonListWebService {
        return retrofit.create(PokemonListWebService::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonListRepository(api: PokemonListWebService): PokemonListRepository {
        return PokemonListRepository(api = api)
    }

    @Provides
    @Singleton
    fun providePokemonDetailWebService(retrofit: Retrofit): PokemonDetailWebService {
        return retrofit.create(PokemonDetailWebService::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonDetailRemote(api: PokemonDetailWebService): PokemonDetailRemote {
        return PokemonDetailRemoteImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor {
        return NetworkMonitor(context = context)
    }
}
