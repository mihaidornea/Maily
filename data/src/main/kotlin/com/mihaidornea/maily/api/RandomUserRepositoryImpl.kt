package com.mihaidornea.maily.api

import com.mihaidornea.maily.model.UserResponse
import com.mihaidornea.maily.repository.RandomUserRepository
import com.mihaidornea.maily.shared.DomainResult
import com.mihaidornea.maily.shared.Failure
import com.mihaidornea.maily.shared.Success
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(
    private val randomUserApi: RandomUserApi,
) : RandomUserRepository {

    override suspend fun getRandomUsers(page: Int): DomainResult<UserResponse> {
        return try {
            Success(randomUserApi.getRandomUsers(page))
        } catch (e: Throwable) {
            Failure(e)
        }
    }
}
