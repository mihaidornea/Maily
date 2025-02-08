package com.mihaidornea.maily.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name") val name: Name,
    @SerializedName("dob") val dateOfBirth: DateOfBirth,
    @SerializedName("picture") val picture: Picture?,
    @SerializedName("nat") val nationality: String,
    @SerializedName("registered") val registered: Registered,
)

data class Name(
    @SerializedName("title") val title: String?,
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String
)

data class DateOfBirth(
    @SerializedName("age") val age: Int
)

data class Registered(
    @SerializedName("date") val date: String,
)

data class Picture(
    @SerializedName("thumbnail") val thumbnail: String?
)
