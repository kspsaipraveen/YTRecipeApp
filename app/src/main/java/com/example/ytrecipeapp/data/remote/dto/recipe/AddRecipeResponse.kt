package com.example.ytrecipeapp.data.remote.dto.recipe

import kotlinx.serialization.Serializable

@Serializable
data class AddRecipeResponse(
    val caloriesPerServing: Int,
    val cookTimeMinutes: Int,
    val cuisine: String,
    val difficulty: String,
    val id: Int,
    val name: String,
    val prepTimeMinutes: Int,
    val servings: Int
)