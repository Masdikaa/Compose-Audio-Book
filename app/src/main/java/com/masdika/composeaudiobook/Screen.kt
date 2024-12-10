package com.masdika.composeaudiobook

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object Menu : Screen("menu_screen")
    object Profile : Screen("profile_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { args ->
                append("/$args")
            }
        }
    }
}