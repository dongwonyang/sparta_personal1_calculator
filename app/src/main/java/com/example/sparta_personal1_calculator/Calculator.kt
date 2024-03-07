package com.example.sparta_personal1_calculator

import androidx.core.text.isDigitsOnly

class Calculator {
    private var num1: Int
    private var num2: Int
    private var signs: MutableList<Char>
    private var nums: MutableList<Int>
    private var inputString: String
    private var negativeIndex : MutableList<Int>

    constructor() {
        num1 = 0
        num2 = 0
        signs = mutableListOf()
        nums = mutableListOf()
        negativeIndex = mutableListOf()
        inputString = ""
    }

    constructor(num1: Int, num2: Int) {
        this.num1 = num1
        this.num2 = num2
        signs = mutableListOf<Char>()
        nums = mutableListOf<Int>()
        negativeIndex = mutableListOf<Int>()
        inputString = ""
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

    fun calculateString(s: String): Int {
        inputString = s
        calculateBracket() // ( ) 계산 다중 괄호 불가능..
        inputString = inputString.replace("(", "")
        inputString = inputString.replace(")", "")

        if(!inputString[0].isDigit()) inputString = "0" + inputString
        signs = inputString.filter { it -> !it.isDigit() }.toMutableList()
        nums = mutableListOf<Int>()
        var tempString = ""
        for (i in inputString) {
            if (i.isDigit()) {
                tempString += i
            } else if (tempString.isNotEmpty()) {
                nums.add(tempString.toInt())
                tempString = ""
            }
        }
        if (tempString.isNotEmpty()) {
            nums.add(tempString.toInt())
        }

        if(negativeIndex.isNotEmpty()) {
            for (i in negativeIndex) {
                nums[i] *= -1
            }
        }

        if(nums.size -1 != signs.size) return 0
        calculateStringPriority()

        return if (nums.size == 1) nums[0] else 0
    }

    private fun calculateStringPriority() {
        var signsPriority = signs.map { it ->
            when (it) {
                '*', 'x', '/' -> 0
                '+', '-' -> 1
                else -> throw java.lang.Exception("계산식 오류 $signs, $nums")
            }
        }.toMutableList<Int>()

        var end = -1
        var length = 0

        for (index in signsPriority.size - 1 downTo 0) {
            val priority = signsPriority[index]

            if (priority == 0 && end == -1) {
                end = index
                length++
            } else if (priority == 0) {
                length++
            }

            if ((index < signsPriority.size-1 && priority != 0 && signsPriority[index + 1] == 0) || (index==0 && end != -1)) {
                calculatePartial(end, length)
                end = -1
                length = 0
            }
            if (priority != 0) {
                end = -1
                length = 0
            }
        }


        calculatePartial(signs.size-1, signs.size)

    }

    private fun calculatePartial(end: Int, length: Int): Int {
        var subNums = nums.subList(end - length + 1, end + 2).toMutableList()
        var subSigns = signs.subList(end - length + 1, end + 1).toMutableList()

        var result = subNums[0]

        for (i in 1 until subNums.size) {
            val num = subNums[i]
            val sign = subSigns[i - 1]

            when (sign) {
                '*', 'x' -> result *= num
                '/' -> result /= num
                '+' -> result += num
                '-' -> result -= num
            }
        }

        for (i in 0 until length) {
            signs.removeAt(end - i)
            nums.removeAt(end + 1 - i)
        }
        nums.set(end - length + 1, result)

        return result
    }

    fun calculateBracket(){
        val cal = Calculator()
        while(true) {
            val startIndex = inputString.indexOf('(')
            val endIndex = inputString.indexOf(')')
            if (startIndex == -1 || endIndex == -1) break

            var bracketModify = cal.calculateString(inputString.substring(startIndex + 1, endIndex)).toString()
            if(bracketModify.toInt() < 0){
                bracketModify = "0-"+(bracketModify.toInt()*-1).toString()
            }
            inputString = inputString.substring(0, startIndex) + bracketModify + inputString.substring(endIndex+1, inputString.length)
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