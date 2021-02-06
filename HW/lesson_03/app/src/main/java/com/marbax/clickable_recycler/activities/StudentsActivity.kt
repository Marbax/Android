package com.marbax.clickable_recycler.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.marbax.clickable_recycler.R
import com.marbax.clickable_recycler.adapters.StudentAdapter
import com.marbax.clickable_recycler.models.Student
import com.oblac.nomen.Nomen
import kotlinx.android.synthetic.main.activity_students.*
import java.util.*

class StudentsActivity : AppCompatActivity() {
    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)

        rvStudents.layoutManager = LinearLayoutManager(this)
        rvStudents.adapter = StudentAdapter(studentsInitializer(100))
    }

    @ExperimentalStdlibApi
    private fun studentsInitializer(count: Int): List<Student> {
        val students = mutableListOf<Student>()
        repeat((0..count).count()) {
            val name = Nomen.randomName().split('_')
            students.add(
                Student(
                    name[1].capitalize(Locale.getDefault()),
                    name[0].capitalize(Locale.getDefault())
                )
            )
        }
        return students
    }
}