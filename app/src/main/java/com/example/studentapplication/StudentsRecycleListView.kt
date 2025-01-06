package com.example.studentapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.PrimaryKey
import androidx.room.Entity
import com.example.studentapplication.model.Model

@Entity
data class Student(
    @PrimaryKey val id: String,
    val name: String,
    val avatarUrl: String,
    var isChecked: Boolean
)

class StudentViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    var nameTextView: TextView?=null
    var idTextView : TextView?=null
    var checkBox: CheckBox?=null
    var student: Student?=null

    init {
        nameTextView = itemView.findViewById(R.id.student_row_name_view)
        idTextView = itemView.findViewById(R.id.student_row_id)
        checkBox = itemView.findViewById(R.id.student_row_checkbox)

        checkBox?.apply{
            setOnClickListener{view->
            (tag as? Int)?.let{tag->
                student?.isChecked = (view as? CheckBox)?.isChecked ?: false
            }
            }
        }

        }
    fun bind(student: Student?, position: Int){
        this.student = student
        nameTextView?.text = student?.name
        idTextView?.text = student?.id
        checkBox?.apply{
            isChecked=student?.isChecked ?: false
            tag=position
        }

    }



}
class StudentRecycleAdapter(private val students : List<Student>?): RecyclerView.Adapter<StudentViewHolder>(){

    override fun getItemCount(): Int = students?.size ?: 0



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflation=LayoutInflater.from(parent.context)
        val view = inflation.inflate(R.layout.student_list_row, parent, false)
        return StudentViewHolder(view);

    }


    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students?.get(position), position)
        val student =students?.get(position)
    }

}


class StudentsRecycleListView : AppCompatActivity() {

    var students: MutableList<Student> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_recycle_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        students=Model.shared.students
        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = StudentRecycleAdapter(students)
        recyclerView.adapter = adapter






        }
    }

