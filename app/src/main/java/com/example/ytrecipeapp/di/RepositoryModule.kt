package com.example.ytrecipeapp.di

import com.example.ytrecipeapp.data.repository.RecipeRepositoryImpl
import com.example.ytrecipeapp.domain.repository.RecipeRepository
import com.example.ytrecipeapp.domain.usecases.GetAllRecipesUseCase
import com.example.ytrecipeapp.domain.usecases.RecipeDetailsByIdUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(
        repository: RecipeRepositoryImpl
    ): RecipeRepository


}