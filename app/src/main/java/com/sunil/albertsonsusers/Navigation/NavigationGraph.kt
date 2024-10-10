package com.sunil.albertsonsusers.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sunil.albertsonsusers.screens.DetailScreen
import com.sunil.albertsonsusers.screens.HomeScreen
import com.sunil.albertsonsusers.screens.UsersScreen
import com.sunil.albertsonsusers.viewModels.UserViewModel

@Composable
fun NavigationGraph(viewModel: UserViewModel) {

    val controller = rememberNavController()

    NavHost(navController = controller, startDestination = "Home") {

        composable("Home"){
            HomeScreen(controller)
        }

        composable("Users/{input}") {backStackEntry ->
            val input = backStackEntry.arguments?.getString("input")?.toInt()
            UsersScreen(viewModel, controller, input)
        }

        composable(
            "UserDetail/{index}",
        ) {backStackEntry ->

            val position = backStackEntry.arguments?.getString("index")?.toInt()
            DetailScreen(position!!,controller,viewModel)
        }
    }


}