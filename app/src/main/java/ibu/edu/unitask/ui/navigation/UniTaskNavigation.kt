package ibu.edu.unitask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ibu.edu.unitask.ui.home.HomeDestination
import ibu.edu.unitask.ui.home.HomeScreen
import ibu.edu.unitask.ui.taskadd.AddNewTaskDestination
import ibu.edu.unitask.ui.taskadd.AddNewTaskScreen

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun UniTaskNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {


        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToAddTask = { navController.navigate(AddNewTaskDestination.route) }
            )
        }

        composable(route= AddNewTaskDestination.route){
            AddNewTaskScreen(
                onNavigateUp = {navController.navigate(HomeDestination.route)}
            )
        }


    }
}