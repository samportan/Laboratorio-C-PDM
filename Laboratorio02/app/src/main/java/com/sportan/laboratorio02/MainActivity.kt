package com.sportan.laboratorio02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var btnCalculate: Button
    private lateinit var bmiTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var infoTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind()

        setListeners()
    }

    private fun bind(){
        weightEditText = findViewById(R.id.weight_edit_text)
        heightEditText = findViewById(R.id.height_edit_text)
        btnCalculate = findViewById(R.id.action_calculate)
        bmiTextView = findViewById(R.id.bmi_text_view)
        resultTextView = findViewById(R.id.result_text_view)
        infoTextView = findViewById(R.id.info_text_view)
    }

    private fun setListeners(){
        btnCalculate.setOnClickListener {
            val weight = weightEditText.text.toString()
            val height = heightEditText.text.toString()

            if(!validateInput(weight, height)) {
                //clearResults()
                return@setOnClickListener
            }

            weightEditText.clearFocus()
            heightEditText.clearFocus()

            val bmi = calculateBMI(weight.toFloat(), height.toFloat())
            val bmiTwoDigits = String.format("%.2f", bmi).toFloat()
            displayResult(bmiTwoDigits)
        }
    }


    private fun calculateBMI(weight: Float, height: Float) : Float {
        return weight / ((height / 100) * (height / 100))
    }

    private fun validateInput(weight: String?, height: String?): Boolean {
        when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }

    private fun displayResult(bmi: Float) {
        bmiTextView.text = bmi.toString()
        infoTextView.text = ("Normal range")

        var informationResult = ""
        var color = 0

        when {
            bmi < 18.50 -> {
                informationResult = "Under Weight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 -> {
                informationResult = "Healthy"
                color = R.color.normal_weight
            }
            bmi in 25.00..29.99 -> {
                informationResult = "Over weight"
                color = R.color.over_weight
            }
            bmi > 29.99 -> {
                informationResult = "Obese"
                color = R.color.obese
            }
        }
        resultTextView.setTextColor(ContextCompat.getColor(this, color))
        resultTextView.text = informationResult
    }



}