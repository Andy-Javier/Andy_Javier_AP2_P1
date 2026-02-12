package com.example.andy_javier_ap2_p1.presentation.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andy_javier_ap2_p1.domain.models.Cerveza
import com.example.andy_javier_ap2_p1.domain.usecase.GetCervezaUseCase
import com.example.andy_javier_ap2_p1.domain.usecase.UpsertCervezaUseCase
import com.example.andy_javier_ap2_p1.domain.usecase.CervezaValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CervezaEditState(
    val idCerveza: Int? = null,
    val nombre: String = "",
    val marca: String = "",
    val puntuacion: String = "1",
    val error: String? = null
)

@HiltViewModel
class CervezaEditViewModel @Inject constructor(
    private val getCervezaUseCase: GetCervezaUseCase,
    private val upsertCervezaUseCase: UpsertCervezaUseCase,
    private val validation: CervezaValidation,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(CervezaEditState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    sealed class UiEvent {
        object SaveSuccess : UiEvent()
        data class ShowSnackbar(val message: String) : UiEvent()
    }

    init {
        // "id" es el nombre del parámetro en Screen.Edit
        val id = savedStateHandle.get<Int>("id")
        if (id != null && id != -1) {
            viewModelScope.launch {
                getCervezaUseCase(id)?.let { cerveza ->
                    state = state.copy(
                        idCerveza = cerveza.idCerveza,
                        nombre = cerveza.nombre,
                        marca = cerveza.marca,
                        puntuacion = cerveza.puntuacion.toString()
                    )
                }
            }
        }
    }

    fun onNombreChange(nombre: String) {
        state = state.copy(nombre = nombre)
    }

    fun onMarcaChange(marca: String) {
        state = state.copy(marca = marca)
    }

    fun onPuntuacionChange(puntuacion: String) {
        if (puntuacion.isEmpty() || (puntuacion.toIntOrNull() != null && puntuacion.toInt() in 1..5)) {
            state = state.copy(puntuacion = puntuacion)
        }
    }

    fun saveCerveza() {
        val puntuacionInt = state.puntuacion.toIntOrNull() ?: 0
        val cerveza = Cerveza(
            idCerveza = state.idCerveza,
            nombre = state.nombre,
            marca = state.marca,
            puntuacion = puntuacionInt
        )

        val validationResult = validation.validate(cerveza)
        if (!validationResult.isValid) {
            state = state.copy(error = validationResult.message)
            viewModelScope.launch {
                _uiEvent.send(UiEvent.ShowSnackbar(validationResult.message ?: "Error de validación"))
            }
            return
        }

        viewModelScope.launch {
            upsertCervezaUseCase(cerveza)
            _uiEvent.send(UiEvent.SaveSuccess)
        }
    }
}
