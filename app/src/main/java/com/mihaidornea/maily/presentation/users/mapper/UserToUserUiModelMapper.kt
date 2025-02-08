package com.mihaidornea.maily.presentation.users.mapper

import com.mihaidornea.maily.model.User
import com.mihaidornea.maily.presentation.users.model.UserUiModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

private const val REGISTERED_TIME_FORMAT = "HH:mm"

class UserToUserUiModelMapper @Inject constructor() {

    fun mapUserToUserUiModel(user: User): UserUiModel {
        var name = "${user.name.first} ${user.name.last}"
        user.name.title?.let { title ->
            name = "$title $name"
        }

        val dateFormatter = DateTimeFormatter.ofPattern(REGISTERED_TIME_FORMAT)
        val registeredDate = Instant.parse(user.registered.date).atZone(ZoneId.systemDefault())
        val registeredTime = dateFormatter.format(registeredDate)

        return UserUiModel(
            name = name,
            age = user.dateOfBirth.age,
            nationality = user.nationality,
            registeredTime = registeredTime,
            pictureUrl = user.picture?.thumbnail
        )
    }
}
