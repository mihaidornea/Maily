package com.mihaidornea.maily.shared.di

import com.mihaidornea.maily.api.RandomUserRepositoryImpl
import com.mihaidornea.maily.repository.RandomUserRepository
import com.mihaidornea.maily.useCases.GetRandomUsersUseCase
import com.mihaidornea.maily.useCases.GetRandomUsersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RandomUserModule {

    @Binds
    @ViewModelScoped
    fun bindRandomUserRepository(impl: RandomUserRepositoryImpl): RandomUserRepository

    @Binds
    fun bindGetRandomUsersUseCase(impl: GetRandomUsersUseCaseImpl): GetRandomUsersUseCase
}
