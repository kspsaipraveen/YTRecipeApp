package com.example.ytrecipeapp.domain.usecases

import com.example.ytrecipeapp.domain.model.Recipe
import com.example.ytrecipeapp.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeDetailsByIdUseCase @Inject constructor(private val repository: RecipeRepository) {

    suspend operator fun invoke(recipeId: Int): Recipe {
        return repository.getRecipeById(recipeId)
    }
}
