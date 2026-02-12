package com.example.andy_javier_ap2_p1.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andy_javier_ap2_p1.data.local.entities.CervezaEntity
import com.example.andy_javier_ap2_p1.data.repository.CervezaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CervezaListViewModel @Inject constructor(
    private val repository: CervezaRepository
) : ViewModel() {

    private val _nombreFiltro = MutableStateFlow<String?>(null)
    private val _marcaFiltro = MutableStateFlow<String?>(null)

    val cervezas: StateFlow<List<CervezaEntity>> =
        combine(_nombreFiltro, _marcaFiltro) { nombre, marca ->
            nombre to marca
        }.flatMapLatest { (nombre, marca) ->
            repository.observeCervezas(nombre, marca)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun setFiltro(nombre: String?, marca: String?) {
        _nombreFiltro.value = nombre
        _marcaFiltro.value = marca
    }
}