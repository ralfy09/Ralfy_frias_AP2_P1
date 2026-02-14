package com.example.ralfy_frias_ap2_p1.domain.UseCase

import com.example.ralfy_frias_ap2_p1.domain.model.Cerveza
import com.example.ralfy_frias_ap2_p1.domain.repository.CervezaRepository

class DeleteCervezaUseCase(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(cerveza: Cerveza) {
        repository.delete(cerveza)
    }
}