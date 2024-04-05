package com.axmedov.yummy

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
val foodNames = arrayOf(
    "Watch All Food",
    "Cheeseburger",
    "Hot Dog",
    "Stake",
    "Potato Free",
    "Pizza",
    "Chicken",
    "Lavash",
)

val foodPrices = mapOf(
    "Watch All Food" to 0.00,
    "Cheeseburger" to 35000.00,
    "Hot Dog" to 26000.00,
    "Stake" to 64000.00,
    "Potato Free" to 23000.00,
    "Pizza" to  48000.00,
    "Chicken" to 38000.000,
    "Lavash" to  32000.00
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropDownMenuBox() {
    val context = LocalContext.current

    var fexpanded by remember { mutableStateOf(false) }
    var selectedText by remember { imageNameState  }
    var selectedImage by remember { imageState }


    Image(
        painter = painterResource(id = selectedImage),
        contentDescription = stringResource(id = R.string.cheesburger_content_description),
        contentScale = ContentScale.Crop,

        modifier = Modifier
            .background(Color.White)
            .size(350.dp)
            .clip(RoundedCornerShape(16.dp))
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = fexpanded,
            onExpandedChange = {
                fexpanded = !fexpanded
            }

        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = fexpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = fexpanded,
                onDismissRequest = { fexpanded = false },
                modifier = Modifier.width(200.dp)

            ) {
                foodNames.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            fexpanded = false

                            selectedImage = when (item) {
                                "Watch All Food" -> R.drawable.watch_all_food
                                "Cheeseburger" -> R.drawable.cheesburger
                                "Hot Dog" -> R.drawable.hot_dog
                                "Stake" -> R.drawable.stake
                                "Potato Free" -> R.drawable.free_with_potato
                                "Pizza" -> R.drawable.pizza
                                "Chicken" -> R.drawable.chicken
                                "Lavash" -> R.drawable.lavash
                                else -> R.drawable.baseline_browser_not_supported_24
                            }

                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()

                        }
                    )
                }
            }
        }
    }
}