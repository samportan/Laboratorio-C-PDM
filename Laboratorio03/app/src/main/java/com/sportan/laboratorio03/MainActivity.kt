package com.sportan.laboratorio03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var counterTextView: TextView
    private lateinit var fiveCentsImage: ImageView
    private lateinit var tenCentsImage: ImageView
    private lateinit var quarterImage: ImageView
    private lateinit var dollarImage: ImageView
    private var counter: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind()
        addListeners()
    }

    fun addListeners(){
        fiveCentsImage.setOnClickListener {
            updateCounter(0.05)
        }
        tenCentsImage.setOnClickListener {
            updateCounter(0.10)
        }
        quarterImage.setOnClickListener {
            updateCounter(0.25)
        }
        dollarImage.setOnClickListener {
            updateCounter(1.0)
        }
    }

    fun updateCounter(money: Double){
        counter = counter + money
        var cTwoDecimals = String.format("%.2f", counter).toString()
        counterTextView.text = cTwoDecimals
    }

    fun bind(){
        counterTextView = findViewById(R.id.counter_text_view)
        fiveCentsImage = findViewById(R.id.five_cents_action)
        tenCentsImage = findViewById(R.id.ten_cents_action)
        quarterImage = findViewById(R.id.quarter_action)
        dollarImage = findViewById(R.id.dollar_action)
    }

}