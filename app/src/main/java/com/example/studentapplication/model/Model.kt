package com.example.studentapplication.model
import com.example.studentapplication.Student
import com.example.studentapplication.StudentsRecycleListView
import com.example.studentapplication.StudentRecycleAdapter

class Model  private constructor(){
    val students: MutableList<Student> = ArrayList()

    companion object{
        val shared =Model()
    }
    init {
        var studentsSize=students.size
        for (i  in 0..studentsSize){
            val student=Student("id$i","name$i",false, "address$i", "phone$i")
            students.add(student)
        }
    }
}