package com.example.ralfy_frias_ap2_p1.presentacion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.ralfy_frias_ap2_p1.presentacion.Edit.EditCervezaScreen
import com.example.ralfy_frias_ap2_p1.presentacion.List.ListCervezaScreen

@Composable
fun CervezaNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LIST_CERVEZA
    ) {

        composable(Routes.LIST_CERVEZA) {
            ListCervezaScreen(
                navController = navController
            )
        }

        composable(
            route = Routes.EDIT_CERVEZA + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id") ?: 0

            EditCervezaScreen(
                navController = navController,
                idCerveza = id
            )
        }
    }
}



