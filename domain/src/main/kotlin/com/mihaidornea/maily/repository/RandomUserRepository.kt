package com.mihaidornea.maily.repository

import com.mihaidornea.maily.model.UserResponse
import com.mihaidornea.maily.shared.DomainResult

interface RandomUserRepository {
    suspend fun getRandomUsers(page: Int): DomainResult<UserResponse>
}
