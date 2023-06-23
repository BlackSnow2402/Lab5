package com.example.lab5

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme

class ResultsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val message = intent.getIntExtra("result", 0)
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Heading(text = "Стоимость печати")
                        PrintRes(amount = message)
                        ClickyOne("Назад")
                    }
                }
            }
        }
    }
}

@Composable
fun PrintRes(amount: Int) {
    Text(
        text = "$amount руб.",
        style = MaterialTheme.typography.displayLarge
    )
}

@Composable
fun ClickyOne(name: String) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        },
        Modifier.padding(20.dp),
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}


