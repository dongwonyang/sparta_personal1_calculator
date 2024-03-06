package com.example.sparta_personal1_calculator

import androidx.core.text.isDigitsOnly

class Calculator {
    private var num1: Int
    private var num2: Int

    init {
        num1 = 0
        num2 = 0
    }

    constructor(num1: Int, num2: Int) {
        this.num1 = num1
        this.num2 = num2
    }

    fun add() {
        AddOperation().run(num1, num2)
    }

    fun sub() {
        SubstractOperation().run(num1, num2)
    }

    fun mul() {
        MultiplyOperation().run(num1, num2)
    }

    fun div() {
        DivideOperation().run(num1, num2)
    }

    fun remain() {
        println("${num1} % ${num2} = ${num1 % num2}")
    }

    fun input(num1: Int, num2: Int) {
        this.num1 = num1
        this.num2 = num2
    }

    fun print() {
        println("num1: ${num1}, num2:${num2}")
    }

    fun calculateString(s: String) {
        val numbers = s.split(" ").filter { it.isDigitsOnly() }.map { it.toInt() }.toIntArray()
        val signs = s.filter { it.isDigit() || it.isWhitespace() }.toCharArray()
        val tempString = s.split(" ").toMutableList()
        val signsPriority = calculateProceed(signs)
        for (i in signs.indices) {
            if (signsPriority[i] == 2) {
                //계산
            }
        }
    }

    fun calculateProceed(sings: CharArray): IntArray {
        val singsPriority = mutableListOf<Int>()
        for (i in sings) {
            when (i) {
                '+', '-' -> singsPriority.add(1)
                '*', '/' -> singsPriority.add(2)
            }
        }
        return singsPriority.toIntArray()
    }

    fun calculateOperation(resultString: MutableList<String>, index: Int) {
        resultString.removeAt(index * 2 + 2)
        resultString.removeAt(index * 2 + 1)
        resultString.removeAt(index * 2)
        when (resultString[index * 2]) {
//            '*', '/' -> 1
//            '+', '-' -> 2
        }
    }
}

interface AbstractOperation {
    fun run(num1: Int, num2: Int)
}

class AddOperation : AbstractOperation {
    override fun run(num1: Int, num2: Int) {
        println("${num1} + ${num2} = ${num1 + num2}")
    }
}

class SubstractOperation : AbstractOperation {
    override fun run(num1: Int, num2: Int) {
        println("${num1} - ${num2} = ${num1 - num2}")
    }
}

class MultiplyOperation : AbstractOperation {
    override fun run(num1: Int, num2: Int) {
        println("${num1} * ${num2} = ${num1 * num2}")
    }
}

class DivideOperation : AbstractOperation {
    override fun run(num1: Int, num2: Int) {
        println("${num1} / ${num2} = ${num1 / num2}")
    }
}