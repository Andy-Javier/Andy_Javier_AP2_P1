package com.example.andy_javier_ap2_p1.domain.usecase

import com.example.andy_javier_ap2_p1.domain.models.Cerveza
import com.example.andy_javier_ap2_p1.domain.repository.CervezaRepository
import javax.inject.Inject

class UpsertCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(cerveza: Cerveza): Int {
        return repository.upsert(cerveza)
    }
}
