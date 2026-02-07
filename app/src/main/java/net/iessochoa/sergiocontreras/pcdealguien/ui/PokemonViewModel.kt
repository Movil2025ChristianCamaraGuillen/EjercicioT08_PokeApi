package net.iessochoa.sergiocontreras.pcdealguien.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.pcdealguien.PokemonApplication
import net.iessochoa.sergiocontreras.pcdealguien.data.PokemonRepository
import retrofit2.Retrofit

class PokemonViewModel(
    pokemonRepository: PokemonRepository
): ViewModel() {
    // Estado para la lista de pokemons
    private val _uiState = MutableStateFlow(PokemonScreenUiState())
    val uiState: StateFlow<PokemonScreenUiState> = _uiState.asStateFlow()

    private val repository = pokemonRepository

    // Función para llamar a la API
    fun fetchPokemonByGeneration(generationId: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { currentState ->
                    currentState.copy(
                        currentState = RequestStatus.isLoading
                    )
                }
                val response = repository.getPokemonsByGeneration(generationId)
                _uiState.update { currentState ->
                    currentState.copy(
                        currentState = RequestStatus.Success(response)
                    )
                }
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        currentState = RequestStatus.Error
                    )
                }
                e.printStackTrace()
            }
        }
    }

    fun onValueChangedEvent(generation: String){
        _uiState.update { currentState ->
            currentState.copy(
                selectedGeneration = generation.toInt()
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokemonApplication)
                val pokemonRepository = application.container.pokemonRepository
                PokemonViewModel(pokemonRepository = pokemonRepository)
            }
        }
    }

}