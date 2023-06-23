@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lab5

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import com.example.compose.AppTheme

val viewModel = PrintViewModel(SavedStateHandle())

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Heading(text = "Стоимость печати")
                        Spacer(modifier = Modifier.height(50.dp))
                        PagesCount()
                        KindRadioGroupUsage()
                        CalculateButton()
                    }
                }
            }
        }
    }
}

@Composable
fun Heading(text: String) {
    Text(
        text = text,
        Modifier.padding(10.dp),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.displayLarge
    )
}

@Composable
fun PagesCount() {
    OutlinedTextField(
        value = viewModel.pagesCount,
        onValueChange = {
            viewModel.update(it)
        },
        Modifier.padding(20.dp),
        label = { Text(text = "Введите кол-во страниц") },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.NumberPassword
        )
    )
}
@Composable
fun KindRadioGroupUsage() {
    val pageFormats = listOf("A4", "A3", "A1")
    val (selected, setSelected) = remember {
        mutableStateOf("")
    }
    Column {
        KindRadioGroup(
            mItems = pageFormats,
            selected, setSelected
        )
        val cost: Int
        when (selected) {
            "A4" -> {
                cost = 10
                viewModel.updateCost(cost)
            }

            "A3" -> {
                cost = 30
                viewModel.updateCost(cost)
            }

            "A1" -> {
                cost = 100
                viewModel.updateCost(cost)
            }
            else -> {
                cost = 0
                viewModel.updateCost(cost)
            }
        }
        Text(
            text = "Стоимость печати: $cost руб./лист",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun KindRadioGroup(
    mItems: List<String>,
    selected: String,
    setSelected: (String) -> Unit,
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            mItems.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selected == item,
                        onClick = {
                            setSelected(item)
                        },
                        enabled = true,
                    )
                    Text(text = item, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}

@Composable
fun CalculateButton() {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(context, ResultsActivity::class.java).apply {
                putExtra("result", Calculate(viewModel.pagesCount.text.toInt(), viewModel.currentCost))
            }
            context.startActivity(intent)
        },
        Modifier.padding(20.dp),
    ) {
        Text(
            text = "Вычислить",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

fun Calculate(pages: Int, cost: Int): Int {
    return pages * cost
}

