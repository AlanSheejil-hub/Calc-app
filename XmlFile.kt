package com.nst.calc

import android.view.LayoutInflater
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


@Composable
fun XmlComposeUi(navController: NavController, viewModel: CalculatorViewModel) {
    val equationLiveDataState = viewModel.equationText.observeAsState()
    val resultLiveDataState = viewModel.resultText.observeAsState()

    //Android view for the xml to compose

    AndroidView(
        //factory contains main layout and the recycler view variable
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.activity_main, null)

            val calc = arrayOf(
                "C",
                "(",
                ")",
                "/",
                "7",
                "8",
                "9",
                "*",
                "4",
                "5",
                "6",
                "+",
                "3",
                "2",
                "1",
                "-",
                "AC",
                "0",
                ".",
                "="
            )
            val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
            recyclerView.layoutManager = GridLayoutManager(view.context, 4)

            // variable to store the adapter listener from the adapter class

            val calcAdapterListener = object : CalculatorAdapter.Listener {
                override fun onClick(position: Int) {
                    val button = calc[position]
                    viewModel.OnButtonClick(btn = button)
                }
            }
            // adapter of the recycler view
            val adapter = CalculatorAdapter(calc, calcAdapterListener)
            recyclerView.adapter = adapter

            //The view variable is called

            view
        },
        modifier = Modifier.fillMaxSize(),

        // Updates the view

        update = { view ->
            val resultTextView: TextView = view.findViewById(R.id.resultText)
            val equationTextView: TextView = view.findViewById(R.id.equationText)

            equationTextView.text = equationLiveDataState.value
            resultTextView.text = resultLiveDataState.value
        }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Button(onClick = {
            navController.navigate("MainContent")
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Back to Home page")
        }
    }
}


