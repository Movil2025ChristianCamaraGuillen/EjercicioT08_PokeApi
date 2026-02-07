package net.iessochoa.sergiocontreras.pcdealguien.network
//1
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationsResponse(
    @SerialName("results") val generations: List<GenerationDto>
)

@Serializable
data class GenerationDto(
    val name: String,
    val url: String
)

@Serializable
data class PokemonsByGenerationResponse(
    @SerialName("pokemon_species") val pokemons: List<PokemonDto>
)

@Serializable
data class PokemonDto(
    val name: String,
    val url: String
)