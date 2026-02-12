package com.example.andy_javier_ap2_p1.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andy_javier_ap2_p1.domain.models.Cerveza
import com.example.andy_javier_ap2_p1.domain.usecase.DeleteCervezaUseCase
import com.example.andy_javier_ap2_p1.domain.usecase.ObserveCervezaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CervezaListState(
    val cervezas: List<Cerveza> = emptyList(),
    val nombreFilter: String = "",
    val marcaFilter: String = "",
    val totalCervezas: Int = 0,
    val promedioPuntuacion: Double = 0.0
)

@HiltViewModel
class CervezaListViewModel @Inject constructor(
    private val observeCervezaUseCase: ObserveCervezaUseCase,
    private val deleteCervezaUseCase: DeleteCervezaUseCase
) : ViewModel() {

    private val _nombreFilter = MutableStateFlow("")
    private val _marcaFilter = MutableStateFlow("")

    val state: StateFlow<CervezaListState> = combine(
        _nombreFilter,
        _marcaFilter
    ) { nombre, marca ->
        nombre to marca
    }.flatMapLatest { (nombre, marca) ->
        val nombreParam = nombre.ifBlank { null }
        val marcaParam = marca.ifBlank { null }
        
        observeCervezaUseCase(nombreParam, marcaParam).map { lista ->
            val total = lista.size
            val promedio = if (lista.isNotEmpty()) {
                lista.sumOf { it.puntuacion.toDouble() } / total
            } else 0.0
            
            CervezaListState(
                cervezas = lista,
                nombreFilter = nombre,
                marcaFilter = marca,
                totalCervezas = total,
                promedioPuntuacion = promedio
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CervezaListState()
    )

    fun onNombreFilterChange(nombre: String) {
        _nombreFilter.value = nombre
    }

    fun onMarcaFilterChange(marca: String) {
        _marcaFilter.value = marca
    }

    fun deleteCerveza(id: Int) {
        viewModelScope.launch {
            deleteCervezaUseCase(id)
        }
    }
}
