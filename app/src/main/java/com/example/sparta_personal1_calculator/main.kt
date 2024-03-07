package com.example.sparta_personal1_calculator

fun main() {
    while (true) {
        println("두 수를 입력하세요.")
        try {
            val inputNumbers = readLine()!!.split(' ').map(String::toInt)
            if (inputNumbers.size < 2) throw NumberFormatException("두개 입력 필요")
            val (a, b) = inputNumbers
            val cal = Calculator(a, b)
            cal.add()
            cal.sub()
            cal.mul()
            cal.div()
            cal.remain()


            println("${cal.calculateString("(1+2+4)/7*3-(4-3)")}")
            println("${cal.calculateString("(5)")}")
            println("${cal.calculateString("(-6)")}")

        } catch (e: NumberFormatException) {
            continue
        } catch (e: ArithmeticException) {
            continue
        } finally {
            println()
        }
    }
}
