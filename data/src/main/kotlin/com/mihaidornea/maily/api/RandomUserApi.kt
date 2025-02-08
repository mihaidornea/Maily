package com.mihaidornea.maily.api

import com.mihaidornea.maily.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val RESULTS_PER_PAGE_DEFAULT = 20
private const val DEFAULT_SEED = "abc"

interface RandomUserApi {
    @GET(".")
    suspend fun getRandomUsers(
        @Query("page")
        page: Int,
        @Query("results")
        results: Int = RESULTS_PER_PAGE_DEFAULT,
        @Query("seed")
        seed: String = DEFAULT_SEED
    ): UserResponse
}
