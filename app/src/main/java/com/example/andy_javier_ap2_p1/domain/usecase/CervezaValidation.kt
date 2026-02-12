package com.example.andy_javier_ap2_p1.domain.usecase

import com.example.andy_javier_ap2_p1.domain.models.Cerveza
import javax.inject.Inject

class CervezaValidation @Inject constructor() {
    fun validate(cerveza: Cerveza): ValidationResult {
        if (cerveza.nombre.isBlank()) {
            return ValidationResult(isValid = false, message = "El nombre es obligatorio")
        }
        if (cerveza.marca.isBlank()) {
            return ValidationResult(isValid = false, message = "La marca es obligatoria")
        }
        if (cerveza.puntuacion < 1 || cerveza.puntuacion > 5) {
            return ValidationResult(isValid = false, message = "La puntuación debe estar entre 1 y 5")
        }
        return ValidationResult(isValid = true)
    }
}

data class ValidationResult(
    val isValid: Boolean,
    val message: String? = null
)
