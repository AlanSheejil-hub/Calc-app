package com.nst.calc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable



class CalculatorViewModel : ViewModel() {

    //Created a live data variable and MutableLiveData  variable

    private val _equationText = MutableLiveData("")
    val equationText: LiveData<String> = _equationText

    private val _resultText = MutableLiveData("0")
    val resultText: LiveData<String> = _resultText


    fun OnButtonClick(btn: String) {
        val cl = MathEvaluator()

        //When statement for the button clicked


        _equationText.value?.let {
            when (btn) {
                "AC" -> {
                    _equationText.value = ""
                    _resultText.value = "0"
                    return
                }

                "C" -> {
                    if (it.isNotEmpty()) {
                        _equationText.value = it.substring(0, it.length - 1)
                        return
                    }
                }

                "=" -> {
                    _equationText.value = _resultText.value
                    return
                }

                else -> _equationText.value = it + btn

            }


        }

        //Calculate result by calling the evaluateExpression function
        try {
            _resultText.value = cl.evaluateExpression(_equationText.value.toString())
        } catch (_: Exception) {
        }


    }


}