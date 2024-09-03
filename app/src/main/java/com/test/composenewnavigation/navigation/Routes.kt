package com.test.composenewnavigation.navigation

import com.test.composenewnavigation.models.Person
import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object Home : Routes()

    @Serializable
    data class DetailScreen(val person: Person) : Routes()

}