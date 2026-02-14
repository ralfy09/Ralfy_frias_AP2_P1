package com.example.ralfy_frias_ap2_p1.domain.UseCase

import com.example.ralfy_frias_ap2_p1.domain.repository.CervezaRepository

class GetCervezasUseCase(
    private val repository: CervezaRepository
) {
    operator fun invoke(nombre: String?, marca: String?) =
        repository.getCervezas(nombre, marca)
}