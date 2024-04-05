package com.axmedov.yummy
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColors
import androidx.compose.material3.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

val LightColorPalette = lightColors(
    primary = Color.Blue,
    background = Color.White,

)

val DarkColorPalette = darkColors(
    primary = Color.Cyan,
    background = Color.Black,
)


val isDarkTheme: MutableState<Boolean> = mutableStateOf(false)


fun toggleTheme () {
    isDarkTheme.value = !isDarkTheme.value
}

fun saveThemePreference(context: Context, isDarkMode: Boolean) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("dark_mode", isDarkMode)
    editor.apply()
}

fun GetThemePreference(context: Context): Boolean {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("dark_mode", false)
}

fun Color.isLight(): Boolean{
    return this.luminance() > 0.5f
}


@Composable
fun DarkTheme(content: @Composable  ()-> Unit) {
    val context = LocalContext.current
    val currentTheme  = if (isDarkTheme.value) DarkColorPalette else LightColorPalette
    MaterialTheme(
        color = currentTheme,
        content = content,
    )
}

@Composable
fun Screen.SecondScreen() {
    var isDarkTheme by remember { mutableStateOf(false) }

    if (isDarkTheme) {
        DarkTheme {
            DarkModeContent(isDarkTheme = isDarkTheme)
        }
    }else {
        LightTheme{
            LightModeContent(isDarkTheme = isDarkTheme)
        }
    }

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = isDarkTheme,
            onCheckedChange = {isChecked ->
                isDarkTheme = isChecked
            }
        )
    }
}

@Composable
fun LightTheme(content: @Composable () -> Unit) {

}

@Composable
fun DarkModeContent(isDarkTheme: Boolean) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
         Text("Dark Mode Content" , color = Color.White)
    }
}

@Composable
fun LightModeContent(isDarkTheme: Boolean) {

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Text("Light Mode Content", color = Color.Black)
    }
    
}

