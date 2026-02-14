package com.example.ralfy_frias_ap2_p1.domain.UseCase


import com.example.ralfy_frias_ap2_p1.domain.repository.CervezaRepository

class GetCervezaByIdUseCase(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(id: Int) =
        repository.getById(id)
}