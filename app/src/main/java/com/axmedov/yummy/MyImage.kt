package com.axmedov.yummy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun MyImage(imageResId: Int) {
    fun Modifier.align(alignment: Alignment): Unit = Unit
    Image(
        painter = painterResource(id = imageResId) , 
        contentDescription = stringResource(id = R.string.cheesburger_content_description),
        contentScale = ContentScale.Crop,

        modifier = Modifier
            .background(Color.White)
            .size(300.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}
