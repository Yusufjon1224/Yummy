package com.axmedov.yummy

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SS(navController = navController)
        }
        composable(route = Screen.SecondScreen.route) {
            SecondScreen()
        }
    }
}

@Composable
fun SS(navController: NavController) {
    SplashScreen()
    LaunchedEffect(Unit) {
        delay(1000)
        navController.navigate(Screen.SecondScreen.route)
    }
}

val imageState = mutableIntStateOf(R.drawable.watch_all_food)
val imageNameState = mutableStateOf("")
var name = mutableStateOf("")
var phoneNumber = mutableStateOf("+")
var count = mutableIntStateOf(0)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 1.dp, top = 1.dp), // Adjust start and top padding as needed
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        val context = LocalContext.current
        var menuExpanded by remember { mutableStateOf(false) }
        var switcher by remember { mutableStateOf(false) }

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Orange,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Yummy Yummy", color = androidx.compose.ui.graphics.Color.White)
                    },
                    actions = {
                        IconButton(
                            onClick = { menuExpanded = !menuExpanded }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_more_vert_24),
                                contentDescription = "Menu"
                            )
                        }
                        // There are drop down menu
                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false },
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_settings_24),
                                            contentDescription = null
                                        )
                                        Text("Settings")
                                    }
                                },
                                onClick = {
                                    Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_category_24),
                                            contentDescription = null
                                        )
                                        Text("Category")
                                    }
                                },
                                onClick = {
                                    Toast.makeText(context, "Category", Toast.LENGTH_SHORT).show()
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_discount_24),
                                            contentDescription = null
                                        )
                                        Text("Discount")
                                    }
                                },
                                onClick = {
                                    Toast.makeText(context, "Discount", Toast.LENGTH_SHORT).show()
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_info_24),
                                            contentDescription = null
                                        )
                                        Text("Info Us")
                                    }
                                },
                                onClick = {
                                    Toast.makeText(context, "Info", Toast.LENGTH_SHORT).show()
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                                            contentDescription = null
                                        )
                                        Text("Exit app")
                                    }
                                },
                                onClick = {
                                    (context as? Activity)?.finish()
                                    //Toast.makeText(context, "Exit app", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                       //Dropdown MenuItem is here
                    }
                )
            }
        ) { innerPadding ->
            if (switcher && !imageNameState.value.isEmpty()) {
                ThirdScreen(innerPadding) { switcher = false }
            } else {
                OrderScreen(
                    innerPadding = innerPadding,
                    phoneNumber = phoneNumber.value,
                    onPhoneNumberChanged = { value ->
                        phoneNumber.value = value
                    },
                    name = name.value,
                    onNameChanged = { value ->
                        name.value = value
                    },
                    switch = { switcher = true },
                    count = count.value,
                    onCountIncreased = { count.value++ }
                ) { count.value-- }
            }
        }

    }

}

@Composable
fun OrderScreen(
    innerPadding: PaddingValues,
    phoneNumber: String,
    onPhoneNumberChanged: (String) -> Unit,
    name: String,
    onNameChanged: (String) -> Unit,
    switch: () -> Unit,
    count: Int,
    onCountIncreased: () -> Unit,
    onCountDecreased: () -> Unit,

) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            text = name,
            onValueChange = onNameChanged,
            label = {
                Text(text = "Enter your name")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            )
        )
        MyOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            text = phoneNumber,
            onValueChange = onPhoneNumberChanged,
            label = {
                Text(text = "Enter phone number")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )


        Demo_ExposedDropDownMenuBox()

        Spacer(modifier = Modifier.padding(bottom = 10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { if (count > 0) onCountDecreased() },
                modifier = Modifier
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color.Orange),
            ) {
                Text(text = "-")
            }
            Text(
                text = "Amount of Foods: $count",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = onCountIncreased,
                modifier = Modifier
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color.Orange),
            ) {
                Text("+")
            }
        }
        Button(
            onClick = switch,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Orange)
        ) {
            Text("Make Order")
        }
    }
}