package com.example.studentapplication.model
import com.example.studentapplication.Student

class Model  private constructor(){
    val students: MutableList<Student> = ArrayList()

    companion object{
        val shared =Model()
    }
    init {
        for (i  in 0..10){
            val student=Student("id$i","name$i","@tools:sample/avatars[0]",false)
            students.add(student)
        }
    }
}