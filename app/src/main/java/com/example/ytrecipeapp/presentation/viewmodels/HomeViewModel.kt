package com.example.ytrecipeapp.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ytrecipeapp.domain.model.Recipe
import com.example.ytrecipeapp.domain.usecases.GetAllRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

import kotlinx.coroutines.launch
import java.lang.Exception

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllRecipesUseCase: GetAllRecipesUseCase): ViewModel() {

//    private val _repository =
//        RecipeRepositoryImpl(apiService = RecipeAPIService(client = `KtorClient----`.client))
//
//    private val getAllRecipesUseCase = GetAllRecipesUseCase(_repository)
    var isLoading by mutableStateOf(false)
        private set

    var categories by mutableStateOf<List<String>>(listOf("All"))
        private set

    var selectedCategory by mutableStateOf<String>("All")
        private set
    var errorMessage: String by mutableStateOf<String>("")
        private set

    var allRecipes by mutableStateOf<List<Recipe>>(emptyList())
        private set

    var selectedRecipes by mutableStateOf<List<Recipe>>(emptyList())


    init {
        fetchAllRecipes()
    }
    fun fetchAllRecipes() {

        isLoading = true
        errorMessage = ""
        viewModelScope.launch {
            try {

                val result = getAllRecipesUseCase()
                allRecipes = result
                val cusines = result.map { it.cuisine }.distinct().sorted()
                categories = listOf("All") + cusines

                applyFilters()
            } catch (e: Exception) {
                errorMessage = e.message ?: "Unknown error"

            } finally {
                isLoading = false
            }
        }
    }


    fun onCategorySelected(category: String) {
        selectedCategory = category
        applyFilters()
    }


    private fun applyFilters() {
        selectedRecipes = if (selectedCategory == "All") {
            allRecipes
        } else {
            allRecipes.filter { it.cuisine == selectedCategory }
        }
    }
}