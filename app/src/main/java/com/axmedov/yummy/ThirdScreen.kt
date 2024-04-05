package com.axmedov.yummy

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.sourceInformationMarkerStart
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerArrayResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import kotlinx.coroutines.NonCancellable

@Composable
fun ThirdScreen(
    innerPadding: PaddingValues,
    onBack: () -> Unit

) {
    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        BackHandler { onBack() }
        // Text(text = "Third Screen")

        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.fast_shopping),
                contentDescription = "Fast order",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Image(
                    painter = painterResource(id = imageState.value),
                    contentDescription = "All Images",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp)
                )
                Text(
                    text = " ${imageNameState.value}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        //.padding(top = 3.dp)
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                )
            }

Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFA500)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Amount of Food: ${count.intValue} ",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier
                        .height(22.dp)
                        .background(Color.White)

                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Total Price: ${count.intValue * foodPrices.getValue(
                                imageNameState.value)}",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier
                        .height(22.dp)
                        .background(color = Color.Blue)
                    )

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Name: ${name.value}",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                     //   Text(text = name.value)
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Phone Number: ${phoneNumber.value}",
                            color = Color.White,
                            fontSize = 20.sp
                        )

                    }

                }
            }
            val context = LocalContext.current

            Button(
                onClick = {
                          sendMessage(
                              context,
                              "998991234567",
                              """
                                  Order: ${imageNameState.value}, ${count.value} piece
                                  Price: ${count.value * (foodPrices.getValue(imageNameState.value))} sum
                                  Name: ${name.value}
                                  Phone Number: ${phoneNumber.value}
                                  """.trimIndent()
                          )
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(com.axmedov.yummy.Color.Orange)
            ) {
                Text(" Order")
            }
        }
    }
}

fun sendMessage(context: Context, phoneNumber: String, message: String) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("smsto:$phoneNumber")
    intent.putExtra("sms_body", message)
    context.startActivity(intent)
}