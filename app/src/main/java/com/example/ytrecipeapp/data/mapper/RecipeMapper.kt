package com.example.ytrecipeapp.data.mapper

import com.example.ytrecipeapp.data.remote.dto.recipe.GetRecipesDTO
import com.example.ytrecipeapp.domain.model.Recipe


fun GetRecipesDTO.toRecipe(): Recipe {
    return Recipe(
        id = id,
        name = name,
        difficulty = difficulty,
        cuisine = cuisine,
        image = image,
        rating = rating,
        ingredients = ingredients,
        mealType = mealType,
        instructions = instructions,
        servings = servings,
        prepTimeMinutes = prepTimeMinutes
    )
}