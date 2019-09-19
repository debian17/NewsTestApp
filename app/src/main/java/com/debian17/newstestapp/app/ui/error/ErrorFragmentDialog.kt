package com.debian17.newstestapp.app.ui.error

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.debian17.newstestapp.R

class ErrorFragmentDialog : DialogFragment() {

    companion object {
        const val TAG = "ErrorFragmentDialogTag"
        private const val ERROR_MESSAGE_KEY = "errorMessageKey"
        fun newInstance(errorMessage: String): ErrorFragmentDialog {
            return ErrorFragmentDialog().apply {
                arguments = Bundle().apply {
                    putString(ERROR_MESSAGE_KEY, errorMessage)
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var errorMessage = arguments?.getString(ERROR_MESSAGE_KEY)

        if (errorMessage == null) {
            errorMessage = getString(R.string.unknown_error_message)
        }

        return AlertDialog.Builder(context!!)
            .setTitle(getString(R.string.error))
            .setMessage(errorMessage)
            .setPositiveButton(getString(R.string.ok)) { _, _ -> dismiss() }
            .create()
    }

}