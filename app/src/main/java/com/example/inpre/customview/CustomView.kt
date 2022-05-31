package com.example.inpre.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import com.example.inpre.R
import com.example.inpre.databinding.BasketCounterBinding
import com.example.inpre.dp2px
import com.google.android.material.shape.RelativeCornerSize

@SuppressLint("CustomViewStyleable")
class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = BasketCounterBinding.inflate(LayoutInflater.from(context), this)

    private val circleSize by lazy { context.dp2px(20f) }
    private val squareSize by lazy { context.dp2px(20f) }

    private val cornerRadius by lazy {
        context.resources.getDimensionPixelSize(R.dimen.margin_8dp).toFloat()
    }

    init {
        with(binding) {
            context.obtainStyledAttributes(attrs, R.styleable.ShapeView).use { typedArray ->
                if (typedArray.getInt(R.styleable.ShapeView_shape, 0) == 0) {
                    imageView.shapeAppearanceModel =
                        imageView.shapeAppearanceModel.withCornerSize(RelativeCornerSize(0.5f))
                    imageView.layoutParams.apply {
                        width = circleSize
                        height = circleSize
                    }
                } else {
                    imageView.layoutParams.apply {
                        width = squareSize
                        height = squareSize
                    }
                    imageView.shapeAppearanceModel =
                        imageView.shapeAppearanceModel.withCornerSize(cornerRadius)
                }
            }
        }
    }
}