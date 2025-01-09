package com.example.studentapplication

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentapplication.model.Model
import com.google.android.material.textfield.TextInputEditText
import com.example.studentapplication.StudentsRecycleListView

class addStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var save = findViewById<Button>(R.id.activity_add_student_Save_Button).setOnClickListener {
            val id = findViewById<TextInputEditText>(R.id.activity_add_student_id_input).text.toString()
            val name = findViewById<TextInputEditText>(R.id.activity_add_student_name_input).text.toString()
            val phone= findViewById<TextInputEditText>(R.id.activity_add_student_phone_input).text.toString()
            val address= findViewById<TextInputEditText>(R.id.activity_add_student_address_input).text.toString()
            val isPresent = findViewById<CheckBox>(R.id.activity_add_student_checkbox).isChecked()
            val student = Student(id, name, isPresent, address, phone)
            Model.shared.students.add(student)
            StudentsRecycleListView().adapter?.notifyDataSetChanged()
            finish()

        }
        var cancelButton=findViewById<Button>(R.id.activity_add_student_cancel_button).setOnClickListener(){
            finish()
        }

    }


}