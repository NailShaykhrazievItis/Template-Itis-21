package com.itis.templateitis

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.itis.templateitis.databinding.DialogEmailBinding

class EmailDialog : DialogFragment() {

    private lateinit var binding: DialogEmailBinding

    var positiveCallback: ((String) -> Unit)? = null
    var negativeCallback: ((String) -> Unit)? = null

    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog = AlertDialog.Builder(requireContext())
        .setView(DialogEmailBinding.inflate(layoutInflater).let {
            binding = it
            it.root
        })
        .setPositiveButton("OK") { dialog, _ ->
            positiveCallback?.invoke("Email: ${binding.etEmail.text}")
        }
        .setNegativeButton("Cancel") { dialog, _ ->
            negativeCallback?.invoke("CANCEL")
            dialog.dismiss()
        }
        .setNeutralButton("More") { dialog, _ ->

        }
        .create()

    companion object {

        fun show(
            fragmentManager: FragmentManager,
            positive: (String) -> Unit,
            negative: (String) -> Unit
        ) {
            EmailDialog().apply {
                positiveCallback = positive
                negativeCallback = negative
                show(fragmentManager, EmailDialog::class.java.name)
            }
        }
    }
}
