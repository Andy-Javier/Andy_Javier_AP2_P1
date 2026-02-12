package com.example.andy_javier_ap2_p1.domain.usecase

import com.example.andy_javier_ap2_p1.domain.models.Cerveza
import com.example.andy_javier_ap2_p1.domain.repository.CervezaRepository
import javax.inject.Inject

class GetCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(id: Int): Cerveza? {
        return repository.getCerveza(id)
    }
}
