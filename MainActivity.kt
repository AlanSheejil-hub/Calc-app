package com.nst.calc


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nst.calc.ui.theme.CalcTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Created a variable for the view model
        val calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            CalcTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "MainContent", builder = {
                    composable("MainContent") {
                        MainPage(navController)
                    }
                    composable("Calc") {
                        ComposeCalculator(navController, calculatorViewModel)
                    }
                    composable("xmlFile") {
                        XmlComposeUi(navController, calculatorViewModel)
                    }

                })
            }
        }
    }


}

