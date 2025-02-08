package com.mihaidornea.maily.presentation.users

import androidx.lifecycle.ViewModel
import com.mihaidornea.maily.useCases.GetRandomUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getRandomUsersUseCase: GetRandomUsersUseCase
) : ViewModel()
