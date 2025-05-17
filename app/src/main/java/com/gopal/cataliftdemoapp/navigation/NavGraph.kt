package com.gopal.cataliftdemoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gopal.cataliftdemoapp.ui.screens.InterestsScreen
import com.gopal.cataliftdemoapp.ui.screens.ProfessionScreen
import com.gopal.cataliftdemoapp.ui.screens.ProfileScreen


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "interests") {
        composable("interests") {
            InterestsScreen(onContinue = { selectedInterests ->
                val interestsStr = selectedInterests.joinToString(",")
                navController.navigate("profession?interests=$interestsStr")
            })
        }

        composable(
            route = "profession?interests={interests}",
            arguments = listOf(navArgument("interests") { type = NavType.StringType })
        ) { backStackEntry ->
            val interests =
                backStackEntry.arguments?.getString("interests")?.split(",") ?: emptyList()
            ProfessionScreen(navController = navController, selectedInterests = interests)
        }

        composable(
            route = "profile?interests={interests}&profession={profession}",
            arguments = listOf(
                navArgument("interests") { type = NavType.StringType },
                navArgument("profession") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val interests =
                backStackEntry.arguments?.getString("interests")?.split(",") ?: emptyList()
            val profession = backStackEntry.arguments?.getString("profession") ?: ""
            ProfileScreen(selectedInterests = interests, selectedProfession = profession)
        }
    }
}
