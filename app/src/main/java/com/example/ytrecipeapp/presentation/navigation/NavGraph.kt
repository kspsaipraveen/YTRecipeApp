package com.example.ytrecipeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ytrecipeapp.presentation.screens.home.HomeScreen
import com.example.ytrecipeapp.presentation.screens.recipe_detail.RecipeDetailScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {

        composable<HomeRoute> {
            HomeScreen(
                onRecipeClick = { recipeId ->
                    navController.navigate(RecipeDetailRoute(recipeId))
                }
            )
        }

        composable<RecipeDetailRoute> { backStackEntry ->

            val detailRoute = backStackEntry.toRoute<RecipeDetailRoute>()
            RecipeDetailScreen(
                recipeId = detailRoute.recipeId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

