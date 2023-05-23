package ibu.edu.unitask

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ibu.edu.unitask.ui.navigation.UniTaskNavHost

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun UniTaskApp(navController: NavHostController = rememberNavController()) {
    UniTaskNavHost(navController = navController)
}