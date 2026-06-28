package com.example.ytrecipeapp.domain.model

data class Recipe(
    val id:Int,
    val name:String,
    val difficulty:String,
    val cuisine:String,
    val image:String,
    val rating: Double,
    val ingredients: List<String>,
    val instructions: List<String>,
    val mealType: List<String>,
    val servings : Int,
    val prepTimeMinutes:Int
    )