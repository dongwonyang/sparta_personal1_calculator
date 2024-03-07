package com.example.sparta_personal1_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv_input = findViewById<TextView>(R.id.tv_calculator_input)
        val tv_result = findViewById<TextView>(R.id.tv_calculator_result)
        tv_result.text = "0"
        tv_input.text = ""
        var btn_map = mutableMapOf<String, Int>()

        for (i in 0..9) {
            val buttonId = resources.getIdentifier("btn_calculator_$i", "id", packageName)
            val buttonText = findViewById<Button>(buttonId).text.toString()
            btn_map[buttonText] = buttonId
        }
        val signs = listOf("add", "sub", "div", "mul", "equal", "bracket1", "bracket2") // point 미구현

        for (i in signs) {
            val buttonId = resources.getIdentifier("btn_calculator_$i", "id", packageName)
            val buttonText = findViewById<Button>(buttonId).text.toString()
            btn_map[buttonText] = buttonId
        }

        val cal = Calculator()
        for((key, value) in btn_map.entries){
            if(key == "="){
                findViewById<Button>(value).setOnClickListener{
                    tv_result.text = cal.calculateString(tv_input.text.toString()).toString()
                    tv_input.text = ""
                }
            } else if(key.isDigitsOnly() || key == "(" || key == ")") {
                findViewById<Button>(value).setOnClickListener {
                    tv_input.text = tv_input.text.toString() + key
                }
            } else{
                findViewById<Button>(value).setOnClickListener {
                    var controlChar = tv_input.text.toString()[tv_input.text.toString().length-1]
                    if(controlChar.isDigit() || controlChar=='(' || controlChar==')') {
                        tv_input.text = tv_input.text.toString() + key
                    }
                }
            }

        }

    }
}

//taskkill /im java.exe /f