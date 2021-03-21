package com.example.mvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvm.adapters.UserAdapter
import com.example.mvvm.databinding.FragmentUsersBinding
import com.example.mvvm.network.GlobalApi
import com.example.mvvm.ui.fragments.dialogs.SystemDFVM
import com.example.mvvm.util.navigate

class UsersF : Fragment() {
    val dialogVM: SystemDFVM by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUsersBinding.inflate(inflater, container, false)
        val vm = UsersFVM(GlobalApi)
        vm.getUsers()
        vm.users.observe(viewLifecycleOwner) { users ->
            binding.rvUsers.adapter = UserAdapter(users){
                navigate(
                    UsersFDirections.actionUsersFToUserInfoF(it),
                    findNavController()
                )
            }
        }
        dialogVM.action.observe(viewLifecycleOwner){
            when(it){
                SystemDFVM.Action.POSITIVE ->
                    vm.getUsers()
                SystemDFVM.Action.NEGATIVE ->
                    activity?.finish()
            }
        }
        return binding.root
    }

}