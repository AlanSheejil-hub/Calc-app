package com.nst.calc

import java.util.Stack

//MathEvaluator class contains function to evaluate expression
class MathEvaluator {

    //evaluateExpression function is used for making calculations
    /**
     * @param expression Only BODMAS math expression supported.
     * @return Evalutation result in whole and decimal format.`sds`
     */
    fun evaluateExpression(expression: String): String {
        /**
         * data
         */
        val chars = expression.toCharArray()
        val values: Stack<Double> = Stack()
        val operator: Stack<Char> = Stack()
        /*
        * Initiating i as 0
        * check if there is a space in chars[i] increment by 1 to skip the space
        */
        var i = 0
        while (i < chars.size) {
            if (chars[i] == ' ') {
                i += 1
                continue
            }
            /*
            * If the chars is a number or "." then send the values to the sb
            * sb is an StringBuilder function
            * then push it to the values stack
            * decrement by 1 to stop the increment of 1 by the i++
            */
            if (chars[i].isDigit()) {
                val sb = StringBuilder()
                while (i < chars.size && (chars[i].isDigit() || chars[i] == '.')) {
                    sb.append(chars[i++])
                }
                values.push(sb.toString().toDouble())
                i -= 1
            }
// If chars is "(" then send it to the operator stack
            else if (chars[i] == '(') {
                operator.push(chars[i])
            }
            /*
            * If chars is ")" then check it has open bracket
            * push the values inside the brackets to values stack by using the applyOP function
            * After the values inside the brackets get calculated now pop the top element of the operator
            */
            else if (chars[i] == ')') {
                while (operator.peek() != '(') {
                    values.push(applyOp(operator.pop(), values.pop(), values.pop()))
                }
                operator.pop()
            }
            /*
            * If the chars is any operator then check if the operator is empty and push it to the operator stack
            * using hasPrecedences function shows that "* or /" has more precedence than "+ or -"
            * push the values for the calculation using the applyOP function
            * */
            else if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/') {

                while (operator.isNotEmpty() && hasPrecedence(chars[i], operator.peek())) {
                    values.push(applyOp(operator.pop(), values.pop(), values.pop()))
                }
                operator.push(chars[i])
            }
            i++
        }
//If the operator is not empty then push the values using the applyOp function

        while (operator.isNotEmpty()) {
            values.push(applyOp(operator.pop(), values.pop(), values.pop()))
        }

//Return the final value by using pop and store it in the variable
        var result = values.pop()

//If the result is Double return it to an string
        return if (result == result.toLong().toDouble()) {
            result.toLong().toString()
//Otherwise it will be an string
        } else {
            result.toString()
        }
    }

    /*
    * applyOp function has three parameters that calculate the values using the function
    * these based on the given operator it calculates
    */
    private fun applyOp(op: Char, a: Double, b: Double): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> if (b != 0.0) a / b else throw ArithmeticException("Cannot divide by zero")
            else -> 0.0
        }
    }

    //hasPrecedences function is used to apply the precedences for the operator
    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        return when (op2) {
            '(', ')' -> false
            '*', -> op1 != '*'
            '/' -> op1 != '/'
            else -> true
        }
    }
}
