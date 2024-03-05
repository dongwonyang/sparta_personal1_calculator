package com.example.sparta_personal1_calculator

fun main(){

    while(true){
        println("두 수를 입력하세요.")
        try {
            val (a, b) = readLine()!!.split(' ').map(String::toInt)
            val cal = Calculator(a, b)
            cal.add()
            cal.sub()
            cal.mul()
            cal.div()
            cal.remain()
            println()
        }catch (e: NumberFormatException) {
            continue
        }
    }
}