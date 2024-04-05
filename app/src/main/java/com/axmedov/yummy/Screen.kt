package com.axmedov.yummy

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object SecondScreen: Screen("second_screen")
    object ThirdScreen: Screen("third_screen")

}