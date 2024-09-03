package com.test.composenewnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.test.composenewnavigation.models.Person
import com.test.composenewnavigation.models.PersonType
import com.test.composenewnavigation.navigation.Routes
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Routes.Home
            ) {

                composable<Routes.Home> {
                    HomeScreen(onDetailClick = { person ->
                        navController.navigate(Routes.DetailScreen(person))
                    })
                }

                composable<Routes.DetailScreen>(
                    typeMap = mapOf(typeOf<Person>() to PersonType)
                ) { entry ->
                    val model = entry.toRoute<Person>()
                    DetailScreen(model.name, model.age, onGoBack = {
                        navController.popBackStack()
                    })
                }

            }
        }
    }
}

@Composable
fun HomeScreen(onDetailClick: (Person) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val name by remember {
            mutableStateOf("Ishfaq")
        }
        Column {
            Text(text = "Name: $name")
            Button(onClick = {
                onDetailClick.invoke(Person(name,22))
            }) {
                Text(text = "Go To Detail")
            }
        }
    }
}

@Composable
fun DetailScreen(name: String, age: Int, onGoBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = "Name: $name")
            Text(text = "Age: $age")
            Button(onClick = {
                onGoBack.invoke()
            }) {
                Text(text = "Go Back")
            }
        }
    }
}
