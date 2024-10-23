package com.example.lr2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuadraticSolverApp()
        }
    }
}

@Composable
fun QuadraticSolverApp() {
    var a by rememberSaveable { mutableStateOf("") }
    var b by rememberSaveable { mutableStateOf("") }
    var c by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Решение квадратного уравнения")

        Text("a * x^2 + b * x + c = 0")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = a,
            onValueChange = { a = it },
            label = { Text("Коэффициент a") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue,
                focusedLabelColor = Color.Blue
            )
        )
        OutlinedTextField(
            value = b,
            onValueChange = { b = it },
            label = { Text("Коэффициент b") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue,
                focusedLabelColor = Color.Blue
            )
        )
        OutlinedTextField(
            value = c,
            onValueChange = { c = it },
            label = { Text("Коэффициент c") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue,
                focusedLabelColor = Color.Blue
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val aVal = a.toIntOrNull()
                val bVal = b.toIntOrNull()
                val cVal = c.toIntOrNull()

                result = if (aVal != null && bVal != null && cVal != null && aVal != 0) {
                    solveQuadraticEquation(aVal, bVal, cVal)
                } else {
                    "Введите корректные коэффициенты"
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            )
        ) {
            Text("Решить")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text(it)
        }
    }
}

fun solveQuadraticEquation(a: Int, b: Int, c: Int): String {
    val discriminant = b * b - 4 * a * c
    return when {
        discriminant > 0 -> {
            val root1 = (-b + sqrt(discriminant.toDouble())) / (2 * a)
            val root2 = (-b - sqrt(discriminant.toDouble())) / (2 * a)
            "Корни: $root1 и $root2"
        }
        discriminant == 0 -> {
            val root = -b / (2 * a)
            "Единственный корень: $root"
        }
        else -> "Нет решения"
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuadraticSolverApp()
}
