package com.example.ralfy_frias_ap2_p1.presentacion.navigation

object Routes {

    const val LIST_CERVEZA = "list_cerveza"
    const val EDIT_CERVEZA = "edit_cerveza"

    fun editCerveza(id: Int): String {
        return "$EDIT_CERVEZA/$id"
    }
}