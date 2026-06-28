package com.example.ytrecipeapp.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ytrecipeapp.presentation.components.ErrorScreen
import com.example.ytrecipeapp.presentation.components.LoadingIndicator
import com.example.ytrecipeapp.presentation.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    onRecipeClick: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = { HomeTopBar() }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            when {
                viewModel.isLoading -> {
                    LoadingIndicator(2.dp)
                }

                !viewModel.errorMessage.equals("") -> {
                    Log.d("ERROR","Error: ${viewModel.errorMessage}")


                    ErrorScreen(
                        errorMsg = viewModel.errorMessage,
                        onRetry = { viewModel.fetchAllRecipes() }
                    )

                }

                else-> {
                    RecipeGrid(
                        viewModel = viewModel,
                        onClick = onRecipeClick
                    )
                }
            }


        }


    }


}


@Composable
fun RecipeGrid(viewModel: HomeViewModel,
               onClick: (Int) -> Unit = {}
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item(span = {
            GridItemSpan(currentLineSpan = maxLineSpan)
        }) {
            HomeHeader()
        }

        if(viewModel.categories.size > 1) {

            item(span = {
                GridItemSpan(currentLineSpan = maxLineSpan)
            }) {
                CategorySection(
                    categories = viewModel.categories,
                    selected = viewModel.selectedCategory,
                    onSelected = { category->
                        viewModel.onCategorySelected(category = category)
                    }
                )

            }
        }

        item(span = {
            GridItemSpan(currentLineSpan = maxLineSpan)
        }){
            SectionHeader(
                title =
                    if (viewModel.selectedCategory == "All") "All Recipes" else viewModel.selectedCategory,
            icon = Icons.Default.MenuOpen
            )
        }


        if(viewModel.allRecipes.isEmpty()){

            item(span = {
                GridItemSpan(currentLineSpan = maxLineSpan)
            }){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ){
                    Text(
                        text = "No Recipes Found",
                        style = MaterialTheme.typography.labelLarge
                            .copy(color = MaterialTheme.colorScheme.onSurface)
                    )
                }
            }
        } else {

            items(viewModel.selectedRecipes, key = {it.id}){ recipe->

                RecipeCard(recipe,
                    onClick =  {onClick(recipe.id)})
            }
        }









    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            Text(
                text = "Recipe",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
        }
    )
}