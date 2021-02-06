package com.marbax.clickable_recycler.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.marbax.clickable_recycler.R
import com.marbax.clickable_recycler.activities.StudentActivity
import com.marbax.clickable_recycler.models.Student
import com.squareup.picasso.Picasso


class StudentAdapter(private val items: List<Student>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val tvSurname = itemView.findViewById<TextView>(R.id.tvSurname)
        private val ivAvatar = itemView.findViewById<ImageView>(R.id.ivAvatar)

        fun initStudent(student: Student) {
            val name: String? = student.name
            this.tvName.text = name
            this.tvSurname.text = student.surname
            Picasso.get()
                .load(PATH)
                .placeholder(
                    AvatarGenerator.avatarImage(
                        this.itemView.context,
                        SIZE,
                        AvatarConstants.CIRCLE,
                        name?.take(HOLDER_LETTERS).toString()
                    )
                )
                .into(this.ivAvatar)

            itemView.setOnClickListener { item ->
                val intent = Intent(item.context, StudentActivity::class.java)
                intent.putExtra(INTENT_STUDENT, student)
                startActivity(item.context, intent, Bundle())
            }
        }

        companion object {
            const val INTENT_STUDENT = "STUDENT"
            const val PATH = "https://brokenfortest"
            const val SIZE = 100
            const val HOLDER_LETTERS = 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StudentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_student, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StudentViewHolder).initStudent(items[position])
    }

}