package com.mihaidornea.maily.useCases

import com.mihaidornea.maily.model.User
import com.mihaidornea.maily.repository.RandomUserRepository
import com.mihaidornea.maily.shared.DomainResult
import com.mihaidornea.maily.shared.map
import javax.inject.Inject

interface GetRandomUsersUseCase {
    suspend operator fun invoke(page: Int): DomainResult<List<User>>
}

class GetRandomUsersUseCaseImpl @Inject constructor(
    private val repository: RandomUserRepository
) : GetRandomUsersUseCase {

    override suspend fun invoke(page: Int): DomainResult<List<User>> =
        repository.getRandomUsers(page).map { it.results }
}
