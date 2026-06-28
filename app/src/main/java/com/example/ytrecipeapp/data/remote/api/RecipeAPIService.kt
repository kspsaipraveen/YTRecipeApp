package com.example.ytrecipeapp.data.remote.api


import com.example.ytrecipeapp.data.remote.dto.recipe.AddRecipeResponse
import com.example.ytrecipeapp.data.remote.dto.recipe.GetAllRecipes
import com.example.ytrecipeapp.data.remote.dto.recipe.GetRecipesDTO
import com.example.ytrecipeapp.data.remote.network.ApiConstants
import com.example.ytrecipeapp.data.remote.dto.recipe.AddRecipeRequest
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject


class RecipeAPIService @Inject constructor(private val client: HttpClient) {


    suspend fun getAllRecipes(): GetAllRecipes {
        return client.get(ApiConstants.BASE_URL + ApiConstants.RECIPES).body()
    }

    suspend fun getRecipeByID(recipeID: Int): GetRecipesDTO {
        return client.get(ApiConstants.BASE_URL + ApiConstants.RECIPES + "/$recipeID").body()
    }

    suspend fun addRecipe(recipe: AddRecipeRequest): AddRecipeResponse {
        return client.post(
            "${ApiConstants.RECIPES}/add"
        ) {
            contentType(ContentType.Application.Json)
            setBody(recipe)
        }.body()
    }

}