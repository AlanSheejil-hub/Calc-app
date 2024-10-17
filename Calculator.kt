package com.nst.calc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// button list function consist the list of buttons

fun buttonList() = listOf(
    "C", "(", ")", "/",
    "7", "8", "9", "*",
    "4", "5", "6", "+",
    "3", "2", "1", "-",
    "AC", "0", ".", "="
)

@Composable
fun ComposeCalculator(navController: NavController, viewModel: CalculatorViewModel) {

/*
* variable is created for observing the state of equationText
* Inside the viewModel
*/

    val equationTextState = viewModel.equationText.observeAsState()
    val resultTextState = viewModel.resultText.observeAsState()
    Box(modifier = Modifier.background(color = Color.Black)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        ) {
            Button(
                onClick = {
                    navController.navigate("MainContent")
                }, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
            ) {
                Text(
                    text = "Back To HomePage",
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = equationTextState.value ?: "",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End,
                    color = Color.White
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = resultTextState.value ?: "",
                style = TextStyle(
                    fontSize = 60.sp,
                    textAlign = TextAlign.End,
                    color = Color.White
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Arranging the button list using the lazy vertical grid

            LazyVerticalGrid(columns = GridCells.Fixed(4), modifier = Modifier.padding(10.dp)) {
                items(buttonList()) {
                    CalButton(btn = it, onClick = {
                        viewModel.OnButtonClick(it)
                    })
                }
            }


        }
    }

}

//This function presents the button on the lazy vertical grid individually

@Composable
fun CalButton(btn: String, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(8.dp)) {

        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier
                .padding(8.dp)
                .size(50.dp),
            containerColor = Color.Black,
            contentColor = Color.White


        ) {
            Text(text = btn, style = TextStyle(fontSize = 30.sp))
        }
    }
}
