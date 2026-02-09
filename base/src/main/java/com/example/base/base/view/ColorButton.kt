package com.example.base.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import com.example.base.R
import com.example.base.databinding.ColorButtonBinding.inflate


class ColorButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
): FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding by lazy{ inflate(LayoutInflater.from(context), this, true) }
    private var text: String = ""
        set(value){
            field = value
            binding.tvText.text = value
        }

    private var marginHorizontally: Int? = null
        set(value) {
            field = value
            updateMargins()
        }
    private var marginVertical: Int? = null
        set(value) {
            field = value
            updateMargins()
        }
    private var marginLeft: Int? = null
        set(value) {
            field = value
            updateMargins()
        }
    private var marginRight: Int? = null
        set(value) {
            field = value
            updateMargins()
        }
    private var marginTop: Int? = null
        set(value) {
            field = value
            updateMargins()
        }
    private var marginBottom: Int? = null
        set(value) {
            field = value
            updateMargins()
        }
    private var buttonStyle: Int = 1
        set(value) {
            field = value
            updateButtonStyle()
        }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ColorButton,
            defStyleAttr,
            defStyleRes
        ).apply {
            text = getString(R.styleable.ColorButton_button_text) ?: ""
            marginHorizontally = getInt(R.styleable.ColorButton_marginHorizontally, 0)
            marginVertical = getInt(R.styleable.ColorButton_marginHorizontally, 0)
            marginLeft= getInt(R.styleable.ColorButton_marginHorizontally, 0)
            marginRight = getInt(R.styleable.ColorButton_marginHorizontally, 0)
            marginTop = getInt(R.styleable.ColorButton_marginHorizontally, 0)
            marginBottom = getInt(R.styleable.ColorButton_marginHorizontally, 0)
        }
    }

    private fun updateMargins(){
        val lp = (binding.root.layoutParams as MarginLayoutParams)
            lp.setMargins(
                marginLeft ?: marginHorizontally ?: lp.leftMargin,
                marginTop ?: marginVertical ?: lp.topMargin,
                marginRight ?: marginHorizontally ?: lp.rightMargin,
                marginBottom ?: marginVertical ?: lp.bottomMargin
            )
        binding.root.layoutParams = lp
    }

    private fun updateButtonStyle(){
        when(buttonStyle){
            ButtonStyle.BLUE.ordinal -> {
                binding.root.setCardBackgroundColor(ButtonStyle.BLUE.color)
            }
        }
    }

    enum class ButtonStyle(
        @get:ColorRes val color: Int
    ){
        BLUE(R.color.color_primary)
    }
}