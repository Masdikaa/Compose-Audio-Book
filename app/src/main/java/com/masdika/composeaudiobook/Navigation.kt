package com.masdika.composeaudiobook

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.masdika.composeaudiobook.ui.MainScreen
import com.masdika.composeaudiobook.ui.PlayScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {

        composable(route = Screen.MainScreen.route) {
            MainScreen(navController)
        }

        composable(
            route = Screen.PlayScreen.route + "/{author}/{title}/{image}",
            arguments = listOf(
                navArgument("author") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
                navArgument("image") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val args = backStackEntry.arguments!!
            val image = URLDecoder.decode(args.getString("image")!!, StandardCharsets.UTF_8.toString()) // Decode URL so it doesn't affect the route

            PlayScreen(
                author = args.getString("author")!!,
                title = args.getString("title")!!,
                image = image,
            )

        }

    }
}