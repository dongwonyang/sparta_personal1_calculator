package com.example.sparta_personal1_calculator

class Calculator {
    var num1: Int = 0
    var num2: Int = 0

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
}

interface AbstractOperation {
    fun run(num1: Int, num2: Int)
}

class AddOperation : AbstractOperation{
    override fun run(num1: Int, num2: Int) {
        println("${num1} + ${num2} = ${num1 + num2}")
    }
}

class SubstractOperation : AbstractOperation{
    override fun run(num1: Int, num2: Int) {
        println("${num1} - ${num2} = ${num1 - num2}")
    }
}

class MultiplyOperation : AbstractOperation{
    override fun run(num1: Int, num2: Int) {
        println("${num1} * ${num2} = ${num1 * num2}")
    }
}

class DivideOperation : AbstractOperation{
    override fun run(num1: Int, num2: Int) {
        println("${num1} / ${num2} = ${num1 / num2}")
    }
}