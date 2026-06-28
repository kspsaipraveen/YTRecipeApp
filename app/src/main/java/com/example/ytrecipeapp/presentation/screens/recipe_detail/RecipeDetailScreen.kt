package com.example.ytrecipeapp.presentation.screens.recipe_detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.ytrecipeapp.presentation.components.LoadingIndicator
import com.example.ytrecipeapp.presentation.components.ErrorScreen
import com.example.ytrecipeapp.presentation.viewmodels.RecipeDetailViewModel

@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    onBack: () -> Unit,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {


    LaunchedEffect(recipeId) {
        viewModel.fetchRecipeById(recipeId)
    }
    Scaffold(

        topBar = {
            RecipeTopBar(
                recipeName = viewModel.recipe?.name ?: "Recipe Details",
                onBack = onBack
            )
        }
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize()
            .padding(innerPadding)
            .background(color = MaterialTheme.colorScheme.surface)
        ) {

            when {
                viewModel.isLoading -> {
                    LoadingIndicator(2.dp)
                }

                !viewModel.errorMessage.equals("") -> {

                    ErrorScreen(
                        errorMsg = viewModel.errorMessage.toString(),
                        onRetry = { viewModel.fetchRecipeById(id = recipeId) }
                    )

                }

                viewModel.recipe !=null ->{
                    // Recipe Details
                    RecipeContent(details = viewModel.recipe!!)

                }
            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeTopBar(
    recipeName: String,
    onBack: () -> Unit
) {


    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {

            Text(
                text = recipeName,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onBack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },


        )

}