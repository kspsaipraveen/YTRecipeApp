package com.example.ytrecipeapp.domain.repository

import com.example.ytrecipeapp.data.remote.dto.recipe.AddRecipeRequest
import com.example.ytrecipeapp.data.remote.dto.recipe.AddRecipeResponse
import com.example.ytrecipeapp.data.remote.dto.recipe.GetRecipesDTO
import com.example.ytrecipeapp.domain.model.Recipe

interface RecipeRepository {

    suspend fun getAllRecipes(): List<Recipe>

    suspend fun getRecipeById(recipeId: Int): Recipe

    suspend fun addRecipe(recipe: AddRecipeRequest): AddRecipeResponse

}