package com.example.tempapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.textfield.TextInputEditText
import androidx.core.content.ContextCompat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity()
{
    private var isCelsiusToFarenheitSelected:Boolean = false
    private var isFarenheitToCelsiusSelected:Boolean = false

    private lateinit var cardViewCelsiusToFarenheit: CardView
    private lateinit var cardViewFarenheitToCelsius: CardView
    private lateinit var textField:TextInputEditText
    private lateinit var resultTextView:TextView

    private lateinit var convertButton: Button
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        initListeners()
        initUI()
    }

    private fun initUI()
    {
        setTemperatureColor()
    }

    private fun initListeners()
    {
        cardViewCelsiusToFarenheit.setOnClickListener {
            changeTemperature()
            setTemperatureColor()
        }

        cardViewFarenheitToCelsius.setOnClickListener {
            changeTemperature()
            setTemperatureColor()
        }

        convertButton.setOnClickListener {
            val temperature = textField.text.toString()
            val result = if(isCelsiusToFarenheitSelected)
            {
                CelsiusToFarenheit(temperature)
            }
            else
            {
                FarenheitToCelsius(temperature)
            }

            resultTextView.text = result
            resultTextView.visibility = View.VISIBLE
        }
    }

    private fun FarenheitToCelsius(temperature: String): String
    {
        val decimalFormat = DecimalFormat("#.##")
        val farenheit = temperature.toDouble()
        val celsius = (farenheit - 32) * 5/9

        return "${farenheit} grados °F son ${decimalFormat.format(celsius)} °C"
    }

    private fun CelsiusToFarenheit(temperature: String): String
    {
        val decimalFormat = DecimalFormat("#.##")
        val celsius = temperature.toDouble()
        val farenheit = (celsius * 9/5) + 32

        return "${celsius} grados °C son ${decimalFormat.format(farenheit)} °F"
    }

    private fun initComponents()
    {
        cardViewCelsiusToFarenheit = findViewById(R.id.card_view_celsius_to_farenheit)
        cardViewFarenheitToCelsius = findViewById(R.id.card_view_farenheit_to_celsius)
        textField = findViewById(R.id.text_field)
        resultTextView = findViewById(R.id.result_text_view)
        convertButton = findViewById(R.id.convert_button)
    }

    private fun changeTemperature()
    {
        if(cardViewCelsiusToFarenheit.isPressed)
        {
            isCelsiusToFarenheitSelected = true
            isFarenheitToCelsiusSelected = false
        }
        else
        {
            isCelsiusToFarenheitSelected = false
            isFarenheitToCelsiusSelected = true
        }
    }

    private fun setTemperatureColor()
    {
        cardViewCelsiusToFarenheit.setCardBackgroundColor(getBackgroundColor(isCelsiusToFarenheitSelected))
        cardViewFarenheitToCelsius.setCardBackgroundColor(getBackgroundColor(isFarenheitToCelsiusSelected))
    }

    private fun getBackgroundColor(isSelectedComponent:Boolean) : Int
    {
        var colorReference = if(isSelectedComponent)
        {
            R.color.TertiaryContainer
        }
        else
        {
            R.color.SecondaryContainer
        }

        return ContextCompat.getColor(this, colorReference)
    }
}