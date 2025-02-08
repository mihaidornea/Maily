package com.mihaidornea.maily.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("results")
    val results: List<User>
)
