package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity(), View.OnClickListener {

    fun getResponseFromCheckBox(answerIsYes: Boolean, answerIsNo: Boolean): String {
        if (!answerIsYes && !answerIsNo) {
            return "No Answer"
        }
        if (answerIsYes) {
            return "Yes"
        }
        return "No"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val input = findViewById<View>(R.id.textInputEditText) as EditText
        val radioYes = findViewById<View>(R.id.radio_yes) as RadioButton
        val radioNo = findViewById<View>(R.id.radio_no) as RadioButton
        var output = findViewById<View>(R.id.output) as TextView

        button.setOnClickListener {
            val message = String.format("Question: %s \nAnswer: %s", input.text.toString(), getResponseFromCheckBox(radioYes.isChecked, radioNo.isChecked))
            Log.v("EditText", message)
            output.text = message
        }

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}