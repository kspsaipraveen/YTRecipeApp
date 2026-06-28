package com.example.ytrecipeapp.data.remote.dto.recipe

import kotlinx.serialization.Serializable

@Serializable
data class GetAllRecipes(
    val limit: Int,
    val recipes: List<GetRecipesDTO>,
    val skip: Int,
    val total: Int
)