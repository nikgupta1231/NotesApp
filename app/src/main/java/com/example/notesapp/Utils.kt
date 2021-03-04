package com.example.notesapp

import android.content.Context
import android.content.res.TypedArray

class Utils {

    companion object {

        fun getRandomColor(context: Context): Int {
            var returnColor: Int = context.getColor(R.color.note_color)
            val arrayId: Int = context.resources.getIdentifier(
                "material_design_colors",
                "array",
                context.packageName
            )

            if (arrayId != 0) {
                val colors: TypedArray = context.resources.obtainTypedArray(arrayId)
                val index = (Math.random() * colors.length()).toInt()
                returnColor = colors.getColor(index, returnColor)
                colors.recycle()
            }
            return returnColor
        }

    }

}