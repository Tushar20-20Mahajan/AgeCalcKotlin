package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInSeconds : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    private var tvAgeInHours : TextView? = null
    private var tvAgeInDays : TextView? = null
    private var tvAgeInYears : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInSeconds = findViewById(R.id.tvAgeInSeconds)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvAgeInHours = findViewById(R.id.tvAgeInHours)
        tvAgeInDays = findViewById(R.id.tvAgeInDays)
        tvAgeInYears = findViewById(R.id.tvAgeInYears)
        btnDatePicker.setOnClickListener {
           clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd =DatePickerDialog( this,
            //DatePickerDialog.OnDateSetListener
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,"Year Selected was $selectedYear    Month Selected was ${selectedMonth+1}          Day Selected was $selectedDayOfMonth",Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                // Another Way -> tvSelectedDate?.setText(selectedDate)
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                theDate?.let {

                    // The DOB in Seconds
                    val selectedDateInSeconds = theDate.time /1000

                    // The DOB in Minutes
                    val selectedDateInMinutes = theDate.time / 60000
                    // Another Way -> val selectedDateInMinutes = theDate.getTime() / 60000

                    // The DOB in Hours
                    val selectedDateInHours = theDate.time / 3600000

                    // The DOB in Days
                    val selectedDateInDays = theDate.time / 86400000

                    // The DOB in Years
                    val selectedDateInYears = theDate.time / (31471200000)

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {

                        // Time in Seconds
                        val currentDateInSeconds = currentDate.time / 1000
                        val differenceInSeconds = currentDateInSeconds - selectedDateInSeconds
                        tvAgeInSeconds?.text = differenceInSeconds.toString()


                        // Time in Minutes
                        val currentDateInMinutes = currentDate.time / 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        tvAgeInMinutes?.text = differenceInMinutes.toString()

                        // Time in Hours
                        val currentDateInHours = currentDate.time / 3600000
                        val differenceInHours = currentDateInHours - selectedDateInHours
                        tvAgeInHours?.text = differenceInHours.toString()

                        // Time in Days
                        val currentDateInDays = currentDate.time / 86400000
                        val differenceInDays = currentDateInDays - selectedDateInDays
                        tvAgeInDays?.text = differenceInDays.toString()

                        // Time in Year
                        val currentDateInYears = currentDate.time / 31471200000
                        val differenceInYears = currentDateInYears - selectedDateInYears
                        tvAgeInYears?.text = differenceInYears.toString()

                    }

                }
            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}