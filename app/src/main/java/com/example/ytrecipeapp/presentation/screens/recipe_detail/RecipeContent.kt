package com.example.ytrecipeapp.presentation.screens.recipe_detail

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material.icons.outlined.Rule
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.ytrecipeapp.domain.model.Recipe

@Composable
fun RecipeContent(details: Recipe) {

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        AsyncImage(
            model = details.image,
            contentDescription = details.name,
            modifier = Modifier.fillMaxWidth().height(250.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            ),
            border = CardDefaults.outlinedCardBorder()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Text(name)

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(24.dp),
                            ),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Icon(
                        imageVector = Icons.Default.Restaurant,
                        contentDescription = "Menu",
                        tint = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = details.name,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Row(cusine, difficulty, meal Type)
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = details.cuisine,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = details.difficulty,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.height(15.dp))


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        RatingBar(details.rating)

                        Spacer(Modifier.width(6.dp))

                        Text(details.rating.toString())
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
        // ingridents
        RecipeDetails(label = "Ingredients", icon = Icons.Default.RestaurantMenu,details.ingredients.toString())

        Spacer(modifier = Modifier.height(15.dp))
        // meal Type
        RecipeDetails(label = "Meal Type", icon = Icons.Filled.Whatshot,details.mealType.getOrNull(0) ?: "Default")


        Spacer(modifier = Modifier.height(15.dp))
        // instructions
        RecipeDetails(label = "Instructions", icon = Icons.Outlined.Rule,details.mealType.getOrNull(0) ?: "Seasonal Food")

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // servings
            RecipeDetails(label = "Servings", icon = Icons.Default.Egg,details.servings.toString() ?: "4")


            // servings
            RecipeDetails(label = "Preparation Time", icon = Icons.Default.Schedule,"${details.servings.toString() ?: 20} minutes" )
        }


    }

}


@Composable
fun RatingBar(rating: Double) {

    Row {

        repeat(5) { index ->

            when {

                index < rating.toInt() -> {

                    Icon(
                        Icons.Default.Star,
                        null,
                        tint = Color(0xFFFFC107)
                    )
                }

                index < rating -> {

                    Icon(
                        Icons.Default.StarHalf,
                        null,
                        tint = Color(0xFFFFC107)
                    )
                }

                else -> {

                    Icon(
                        imageVector = Icons.Default.StarBorder,
                        contentDescription = null,
                        tint = Color(0xFFFFC107)
                    )
                }
            }
        }
    }
}