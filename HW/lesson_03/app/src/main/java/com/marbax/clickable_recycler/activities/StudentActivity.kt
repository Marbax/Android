package com.marbax.clickable_recycler.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.marbax.clickable_recycler.R
import com.marbax.clickable_recycler.adapters.StudentAdapter
import com.marbax.clickable_recycler.models.Student

class StudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        val student: Student? =
            intent.getParcelableExtra(StudentAdapter.StudentViewHolder.Companion.INTENT_STUDENT)
        val tvName = findViewById<TextView>(R.id.tvConcreteName)
        tvName.text = student?.name
        val tvSurname = findViewById<TextView>(R.id.tvConcreteSurname)
        tvSurname.text = student?.surname

    }
}