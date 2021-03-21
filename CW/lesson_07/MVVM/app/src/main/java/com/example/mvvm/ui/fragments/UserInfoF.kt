package com.example.mvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mvvm.databinding.FragmentUserInfoBinding
import com.example.mvvm.network.GlobalApi
import com.example.mvvm.ui.fragments.dialogs.SystemDFVM
import com.example.mvvm.util.navigate

class UserInfoF : Fragment() {

    private val args: UserInfoFArgs by navArgs()
    private val dialogVM: SystemDFVM by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        val vm = UserInfoFVM(GlobalApi)
        vm.getUserInfo(args.login)
        vm.user.observe(viewLifecycleOwner) {
            binding.user = it
        }

        vm.error.observe(viewLifecycleOwner) {
            navigate(
                UserInfoFDirections.actionGlobalToSystemDialog(it),
                findNavController()
            )
        }

        dialogVM.action.observe(viewLifecycleOwner){
            when(it){
                SystemDFVM.Action.POSITIVE ->
                    vm.getUserInfo(args.login)
                SystemDFVM.Action.NEGATIVE ->
                    activity?.finish()
            }
        }
        return binding.root
    }

}