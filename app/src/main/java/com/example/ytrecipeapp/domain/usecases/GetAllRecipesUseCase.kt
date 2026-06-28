package com.example.ytrecipeapp.domain.usecases

import com.example.ytrecipeapp.data.remote.dto.recipe.GetRecipesDTO
import com.example.ytrecipeapp.domain.model.Recipe
import com.example.ytrecipeapp.domain.repository.RecipeRepository
import javax.inject.Inject

class GetAllRecipesUseCase @Inject constructor(private val repository: RecipeRepository) {


    suspend operator fun invoke(): List<Recipe> {
        return repository.getAllRecipes()
    }
}