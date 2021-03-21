package com.example.mvvm.ui.fragments.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.mvvm.databinding.DialogSystemBinding

class SystemDF : DialogFragment() {
    private val vm: SystemDFVM by activityViewModels()
    private val args: SystemDFArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setCancelable(false)
        val binding = DialogSystemBinding.inflate(inflater, container, false)
        binding.apply {
            dialog = args.dialog
            positiveButton.setOnClickListener {
                vm.positive()
                dismiss()
            }
            negativeButton.setOnClickListener {
                vm.negative()
                dismiss()
            }
        }
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}