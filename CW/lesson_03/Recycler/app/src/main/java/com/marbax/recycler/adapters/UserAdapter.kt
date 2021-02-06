package com.marbax.recycler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.marbax.recycler.R
import com.marbax.recycler.models.User
import com.squareup.picasso.Picasso

class UserAdapter(private val users: List<User>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.TVName)
        private val tvSurname: TextView = itemView.findViewById(R.id.TVSurname)
        private val avatar: ImageView = itemView.findViewById(R.id.IMAvatar)

        fun initUser(user: User) {
            val uName: String = user.name
            this.tvName.text = uName
            this.tvSurname.text = user.surname
            Picasso.get()
                .load(PATH)
                .placeholder(
                    AvatarGenerator.avatarImage(
                        this.itemView.context,
                        SIZE,
                        AvatarConstants.CIRCLE,
                        uName.take(1)
                    )
                )
                .into(this.avatar)
        }
    }

    class ImgsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatarF: ImageView = itemView.findViewById(R.id.IMFirst)
        private val avatarS: ImageView = itemView.findViewById(R.id.IMSecond)

        fun initUser(user: User) {
            Picasso.get()
                .load(PATH)
                .placeholder(
                    AvatarGenerator.avatarImage(
                        this.itemView.context,
                        SIZE,
                        AvatarConstants.RECTANGLE,
                        "A"
                    )
                )
                .into(this.avatarF)

            Picasso.get()
                .load(PATH)
                .placeholder(
                    AvatarGenerator.avatarImage(
                        this.itemView.context,
                        SIZE,
                        AvatarConstants.RECTANGLE,
                        "B"
                    )
                )
                .into(this.avatarS)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType % 2 != 0) {
            UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
            )

        } else {
            ImgsViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.rv_item_img, parent, false)
            )
        }

    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position % 2 != 0) {
            (holder as UserViewHolder).initUser(users[position])
        } else {
            (holder as ImgsViewHolder).initUser(users[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    companion object {
        const val SIZE = 200
        const val PATH = "https://brokenfortest"

    }
}