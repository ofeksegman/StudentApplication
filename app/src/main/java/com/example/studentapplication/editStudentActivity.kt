package com.example.studentapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentapplication.model.Model
import com.google.android.material.textfield.TextInputEditText

class editStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
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

        findViewById<TextInputEditText>(R.id.activity_edit_student_name_input).setText(studentName)
        findViewById<TextInputEditText>(R.id.activity_edit_student_id_input).setText(studentId)
        findViewById<TextInputEditText>(R.id.activity_edit_student_address_input).setText(studentAddress)
        findViewById<TextInputEditText>(R.id.activity_edit_student_phone_input).setText(studentPhone)
        findViewById<CheckBox>(R.id.activity_edit_student_details_checkbox).isChecked=studentIsChecked
        var pos=intent.getIntExtra("STUDENT_INDEX",0)
        var save = findViewById<Button>(R.id.activity_edit_save_button).setOnClickListener {
            val id = findViewById<TextInputEditText>(R.id.activity_edit_student_id_input).text.toString()
            val name = findViewById<TextInputEditText>(R.id.activity_edit_student_name_input).text.toString()
            val phone= findViewById<TextInputEditText>(R.id.activity_edit_student_phone_input).text.toString()
            val address= findViewById<TextInputEditText>(R.id.activity_edit_student_address_input).text.toString()
            val isPresent = findViewById<CheckBox>(R.id.activity_edit_student_details_checkbox).isChecked
            val student = Student(id, name, isPresent, address, phone)
            Model.shared.students[pos]=student
            StudentsRecycleListView().adapter?.notifyDataSetChanged()
            val intent = Intent(this, StudentsRecycleListView::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
           // intent.setClass(this, StudentsRecycleListView::class.java)
            startActivity(intent)
            //finish()
        }
        var cancelButton=findViewById<Button>(R.id.activity_edit_cancel_button).setOnClickListener(){
            finish()
        }
        var deleteButton=findViewById<Button>(R.id.activity_edit_delete_button).setOnClickListener(){
            Model.shared.students.removeAt(pos)
            StudentsRecycleListView().adapter?.notifyDataSetChanged()
            val intent = Intent(this, StudentsRecycleListView::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

       var backButton=findViewById<ImageButton>(R.id.activity_student_edit_back).setOnClickListener(){
            finish()
        }


    }
}