package com.example.ytrecipeapp.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ytrecipeapp.data.remote.api.RecipeAPIService
import com.example.ytrecipeapp.data.repository.RecipeRepositoryImpl
import com.example.ytrecipeapp.domain.model.Recipe
import com.example.ytrecipeapp.domain.usecases.RecipeDetailsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(private val recipeDetailsByIdUseCase: RecipeDetailsByIdUseCase): ViewModel() {

    private val repository =
        RecipeRepositoryImpl(apiService = RecipeAPIService(client = `KtorClient----`.client))

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var recipe by mutableStateOf<Recipe?>(null)
        private set


    fun fetchRecipeById(id:Int){
        isLoading = true
        errorMessage = ""
        try {
            viewModelScope.launch {
                recipe = recipeDetailsByIdUseCase(id)
            }
        }catch (e : Exception){
            errorMessage = e.message ?: "Unknown error"
        } finally {
            isLoading = false
        }
    }
}