package com.mihaidornea.maily.presentation.users.model

data class UserUiModel(
    val name: String,
    val age: Int,
    val nationality: String,
    val registeredTime: String,
    val pictureUrl: String?
)
