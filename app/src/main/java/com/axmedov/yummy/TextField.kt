package com.axmedov.yummy

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyOutlinedTextField(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)?,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = onValueChange,
        label = label,
        keyboardOptions = keyboardOptions
    )
}