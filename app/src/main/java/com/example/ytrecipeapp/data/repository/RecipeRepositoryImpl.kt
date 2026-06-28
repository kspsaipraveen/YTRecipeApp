package com.example.ytrecipeapp.data.repository

import com.example.ytrecipeapp.data.mapper.toRecipe
import com.example.ytrecipeapp.data.remote.api.RecipeAPIService
import com.example.ytrecipeapp.data.remote.dto.recipe.AddRecipeRequest
import com.example.ytrecipeapp.data.remote.dto.recipe.AddRecipeResponse
import com.example.ytrecipeapp.data.remote.dto.recipe.GetRecipesDTO
import com.example.ytrecipeapp.domain.model.Recipe
import com.example.ytrecipeapp.domain.repository.RecipeRepository
import javax.inject.Inject


class RecipeRepositoryImpl @Inject constructor(private val apiService: RecipeAPIService) : RecipeRepository {
    override suspend fun getAllRecipes(): List<Recipe> {
        val dtoList : List<GetRecipesDTO> = apiService.getAllRecipes().recipes
        return dtoList.map { dto->
            dto.toRecipe()
        }
    }

    override suspend fun getRecipeById(recipeId: Int): Recipe {
        val dto =  apiService.getRecipeByID(recipeId)
        return dto.toRecipe()
    }

    override suspend fun addRecipe(recipe: AddRecipeRequest): AddRecipeResponse {
        return apiService.addRecipe(recipe)
    }
}