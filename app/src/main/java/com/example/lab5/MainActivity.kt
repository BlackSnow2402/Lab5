@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lab5

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
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.compose.md_theme_dark_primary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val pageFormats = listOf(
                PageFormat("A4", 10),
                PageFormat("A3", 30),
                PageFormat("A1", 100),
            )
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                    ) {
                        Heading(text = "Стоимость печати")
                        Spacer(modifier = Modifier.height(50.dp))
                        PagesCount()
                        PageFormatsRadioGroup(pageFormats)
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
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        Modifier.padding(20.dp),
        label = { Text(text = "Введите кол-во страниц") },

    )
}

@Composable
fun PageFormatRadioButton(pageFormat: PageFormat) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(10.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically,
    )
    {
        RadioButton(selected = false, onClick = { /*TODO*/ })
        Text(
            text = pageFormat.Name,
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = pageFormat.Price.toString() + " руб./стр",
            Modifier.padding(20.dp),
            color = MaterialTheme.colorScheme.tertiary,
        )
    }

}

@Composable
fun PageFormatsRadioGroup(pageFormats: List<PageFormat>) {
    Column(Modifier.selectableGroup()) {
        pageFormats.forEach{ pageFormat -> PageFormatRadioButton(pageFormat) }
    }
}

@Composable
fun CalculateButton() {
    Button(
        onClick = {

        },
        Modifier.padding(20.dp),
    ) {
        Text(
            text = "Вычислить",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

