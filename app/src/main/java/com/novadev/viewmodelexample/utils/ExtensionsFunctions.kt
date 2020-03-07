package com.novadev.viewmodelexample.utils

import android.app.AlertDialog
import android.content.res.Resources
import android.widget.EditText
import android.widget.FrameLayout

val Float.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()


 // Alert dialog with edit text and margin extension functions
fun AlertDialog.Builder.setEditText(editText: EditText): AlertDialog.Builder {
    val container = FrameLayout(context)
    container.addView(editText)
    val containerParams = FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    )
    val marginHorizontal = 48F
    val marginTop = 16F
    containerParams.topMargin = (marginTop / 2).toPx
    containerParams.leftMargin = marginHorizontal.toInt()
    containerParams.rightMargin = marginHorizontal.toInt()
    container.layoutParams = containerParams

    val superContainer = FrameLayout(context)
    superContainer.addView(container)

    setView(superContainer)

    return this
}