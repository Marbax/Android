package com.example.mvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.databinding.RvItemBinding
import com.example.mvvm.models.User

class UserAdapter(private val users: List<User>, val onClickListener: (String) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        return UserVH(RvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: UserVH, position: Int) {
        users[position].let { holder.bind(it) }
        holder.itemView.setOnClickListener{onClickListener(users[position].login)}
    }

    override fun getItemCount() = users.size

    class UserVH(private val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
        }
    }
}
