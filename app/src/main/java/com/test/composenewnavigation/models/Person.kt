package com.test.composenewnavigation.models

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
@Parcelize
data class Person(
    val name: String = "Ishfaq",
    val age: Int = 22
) : Parcelable

val PersonType = object : NavType<Person>(false) {
    override fun get(bundle: Bundle, key: String): Person? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, Person::class.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): Person {
        return Json.decodeFromString(value)
    }

    override fun serializeAsValue(value: Person): String {
        return Json.encodeToString(value)
    }

    override fun put(bundle: Bundle, key: String, value: Person) {
        bundle.putParcelable(key, value)
    }

}
