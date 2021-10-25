package com.itis.templateitis

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

fun Fragment.showDialogExt(
    title: String = "",
    message: String = "",
    negativeAction: () -> Unit = {},
    positiveAction: () -> Unit = {},
) {
    AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("OK") { dialog, _ ->
            positiveAction()
        }
        .setNegativeButton("Cancel") { dialog, _ ->
            negativeAction()
            dialog.dismiss()
        }
        .show()
}
