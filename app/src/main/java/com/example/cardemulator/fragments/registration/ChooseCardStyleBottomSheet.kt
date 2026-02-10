package com.example.cardemulator.fragments.registration

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChooseCardStyleBottomSheet(
    private val onDismissCallback: () -> Unit
): BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog?.setOnShowListener { dialog ->
            val bottomSheetDialog = dialog as BottomSheetDialog
            val bottomSheetInternal = bottomSheetDialog.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            ) ?: return@setOnShowListener

            val behavior = BottomSheetBehavior.from(bottomSheetInternal)

            // Расширяем на весь экран
            bottomSheetInternal.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            bottomSheetInternal.requestLayout()

            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isFitToContents = true  // Важный параметр для полного растяжения
            behavior.skipCollapsed = true
        }
    }
}