package com.example.studentapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity
data class Student(
    @PrimaryKey val id: String,
    val name: String,
    val avatarUrl: String,
    var isChecked: Boolean
)

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    private val students = mutableListOf<Student>()

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define views here, e.g., TextView for name, ImageView for profile, etc.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_students_recycle_list_view, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        // Bind data to views here
    }

    override fun getItemCount(): Int = students.size

    // Method to get the list of students (if needed)
    fun getStudents(): List<Student> = students

    // Method to add students
    fun addStudent(student: Student) {
        students.add(student)
        notifyItemInserted(students.size - 1)
    }

    fun remove(student: Student) {
        students.remove(student)
    }
}


class StudentsRecycleListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_recycle_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }


        val list=findViewById<RecyclerView>(R.id.student_list);
        val studentAdapter  = StudentAdapter()
        // Add a new student
        studentAdapter.addStudent(Student("1", "John Doe", "https://randomuser.me/api", false));

        list.adapter = studentAdapter
        list.layoutManager = LinearLayoutManager(this)






        }
    }

