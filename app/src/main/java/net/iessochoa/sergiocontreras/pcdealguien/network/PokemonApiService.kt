package net.iessochoa.sergiocontreras.pcdealguien.network
//2
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("generation")
    suspend fun getGenerations(): GenerationsResponse

    @GET("generation/{id}/")
    suspend fun getPokemonsByGeneration(@Path("id") id: Int): PokemonsByGenerationResponse
}