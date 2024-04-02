package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Состояние для хранения введенной пользователем строки
            val message = remember { mutableStateOf("") }

            // добаление скролла
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            ) {

            // текстовое поле
                Text(
                    text = "Всего символов: ${message.value.length}",
                    fontSize = 20.sp
                )
                // инпут
                TextField(
                    value = message.value,
                    textStyle = TextStyle(fontSize = 25.sp),
                    onValueChange = { newText -> message.value = newText }
                )
                // текстовое поле
                Text(
                    text = "Количество каждого символа:",
                    fontSize = 20.sp
                )
                // подсчёт каждого символа и отображение в текстовом поле
                val charCounts = countCharacters(message.value)
                charCounts.forEach { (char, count) ->
                    Text(
                        text = "'$char' : $count",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }

    // логика подсчёта символов
    private fun countCharacters(text: String): Map<Char, Int> {
        // хранение кол-ва символов
        val charCounts = mutableMapOf<Char, Int>()
        // итерация символов
        for (char in text.filterNot { it == ' ' }) {
            // увеличение счетчика
            charCounts[char] = charCounts.getOrDefault(char, 0) + 1
        }
        return charCounts
    }
}

@Composable
fun Preview() {
    MyApplicationTheme {
        MainActivity()
    }
}
