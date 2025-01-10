package com.example.studentapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val studentName=intent.getStringExtra("STUDENT_NAME")
        val studentId=intent.getStringExtra("STUDENT_ID")
        val studentAddress=intent.getStringExtra("STUDENT_ADDRESS")
        val studentPhone=intent.getStringExtra("STUDENT_PHONE")
        val studentIsChecked=intent.getBooleanExtra("STUDENT_IS_CHECKED", false)
        val position=intent.getIntExtra("STUDENT_INDEX",0)

        findViewById<TextView>(R.id.activity_student_details_name).text="Name: $studentName"
        findViewById<TextView>(R.id.activity_student_details_id).text="ID: $studentId"
        findViewById<TextView>(R.id.activity_student_details_address).text="Address: $studentAddress"
        findViewById<TextView>(R.id.activity_student_details_phone).text="Phone: $studentPhone"
        findViewById<CheckBox>(R.id.activity_student_details_checkbox).isChecked=studentIsChecked
        findViewById<CheckBox>(R.id.activity_student_details_checkbox).isEnabled=false
        findViewById<ImageButton>(R.id.activity_student_details_back).setOnClickListener {
            finish()
        }
        findViewById<Button>(R.id.activity_student_details_edit_button).setOnClickListener {
            val intent = intent
            intent.setClass(this, editStudentActivity::class.java).apply {
                    putExtra("STUDENT_ID", studentId)
                    putExtra("STUDENT_NAME", studentName)
                    putExtra("STUDENT_ADDRESS", studentAddress)
                    putExtra("STUDENT_PHONE", studentPhone)
                    putExtra("STUDENT_IS_CHECKED", studentIsChecked)
                    putExtra("STUDENT_INDEX", position)
                }
            startActivity(intent)
        }
        }

    }
