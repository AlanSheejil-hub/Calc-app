package com.nst.calc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun MainPage(navController: NavController) {
    Column {
        Text(text = "Select any one of the following",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(30.dp))

        Column(modifier = Modifier.padding(10.dp)) {
            Button(onClick = {
             navController.navigate("xmlFile")
            }) {
                Text(text = "XML File")
            }

        }
        Column(modifier = Modifier.padding(10.dp)) {


            Button(onClick = {
             navController.navigate("Calc")
            }) {
                Text(text = "Compose File")
            }
        }


    }

}


