package net.iessochoa.sergiocontreras.pcdealguien.ui
//7
import net.iessochoa.sergiocontreras.pcdealguien.network.PokemonDto


data class PokemonScreenUiState (
    val selectedGeneration: Int = 1,
    val totalGeneration: Int = 1,
    val currentState: RequestStatus = RequestStatus.Idle
    )

sealed interface RequestStatus{
    object isLoading: RequestStatus
    data class Success(val pokemonList: List<PokemonDto>): RequestStatus
    object Error : RequestStatus
    object Idle: RequestStatus
}