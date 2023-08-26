package com.example.custoprogressbarapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class CustomProgressBar(context: Context, attrs: AttributeSet) : View(context, attrs) {
    companion object {
        private const val DEFAULT_BACKGROUND_COLOR = Color.BLACK
        private const val DEFAULT_PROGRESS_BORDER_COLOR = Color.WHITE
        private const val DEFAULT_PROGRESS_COLOR = Color.WHITE
        private const val DEFAULT_PROGRESS_BORDER_WIDTH = 4.0f

        private const val DEFAULT_PROGRESS = 0
    }


    //Setup default values of the XML attribute properties, in case a user of the
    // custom view does not set one of them
    private var backgroundColor = DEFAULT_BACKGROUND_COLOR
    private var progressBorderColor = DEFAULT_PROGRESS_BORDER_COLOR
    private var progressBorderWidth = DEFAULT_PROGRESS_BORDER_WIDTH

    private val paint = Paint()
    private var width = 0
    private var height = 0

    //Add a new property called progress for the progress state.
    var progress = DEFAULT_PROGRESS
        set(state) {
            field = state
            //The invalidate() method makes Android redraw the view by calling onDraw()
            invalidate()
        }

    var progressColor = DEFAULT_PROGRESS_COLOR
        set(state) {
            field = state
            //The invalidate() method makes Android redraw the view by calling onDraw()
            invalidate()
        }
    //Call a new private setupAttributes() method from the init block
    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }


    private fun setupAttributes(attrs: AttributeSet) {
        // Obtain a typed array of attributes
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.CustomProgressBar,
            0, 0
        )

        // Extract custom attributes into member variables
        progress = typedArray.getInt(R.styleable.CustomProgressBar_progress, DEFAULT_PROGRESS)
        backgroundColor = typedArray.getColor(
            R.styleable.CustomProgressBar_backgroundColor,
            DEFAULT_BACKGROUND_COLOR
        )
        progressBorderColor = typedArray.getColor(
            R.styleable.CustomProgressBar_progressBorderColor,
            DEFAULT_PROGRESS_BORDER_COLOR
        )
        progressColor =
            typedArray.getColor(R.styleable.CustomProgressBar_progressColor, DEFAULT_PROGRESS_COLOR)
        progressBorderWidth = typedArray.getDimension(R.styleable.CustomProgressBar_progressBorderWidth, DEFAULT_PROGRESS_BORDER_WIDTH)

        // TypedArray objects are shared and must be recycled.
        typedArray.recycle()
    }

    //Override the onMeasure() method to provide an accurate and efficient measurement of the view contents
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width = measuredWidth
        height = measuredHeight

        //Use setMeasuredDimension(int, int) to store the measured width and measured height of the view
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setBackgroundColor(backgroundColor)
        drawProgressBarLevel(canvas)
    }

    private fun drawProgressBarLevel(canvas: Canvas) {
        if(progress<0 || progress>100){
            progress = DEFAULT_PROGRESS
        }

        Log.d("TAG", "$progress")
        //Set the paint color to the progressBorderColor and make it
        //just draw a border around the drawing area by setting the style to STROKE
        paint.color = progressBorderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = progressBorderWidth

        //Draw the border. All dimensions are measured from the top left edge
        canvas.drawRect(
            width * 0.10f,
            width * 0.10f,
            width * 0.90f,
            height - width * 0.10f,
            paint
        )

        //Change the paint color to the progressColor and make it fill the drawing area
        paint.color = progressColor
        paint.style = Paint.Style.FILL
        val progressLevel = (height - width * 0.10f) - (height - width * 0.20f) * (progress / 100f)

        //Draw the background. All dimensions are measured from the top left edge
        canvas.drawRect(
            width * 0.10f,
            progressLevel,
            width * 0.90f,
            height - width * 0.10f,
            paint
        )
    }
}