package com.ruthwikkk.widget.donutchart

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.ruthwikkk.widget.donutchart.R
import kotlin.math.min

class DonutProgressView: View {

    private var mHeight = 0f
    private var mWidth = 0f

    val pFactor = 3.6f
    var angle = 360f
    var arcWidth = 200f
    var defColour = 0xffffffff
    var arcColour = 0xff000000
    var textColour = 0xff000000
    var textPadding = 20f

    var mProgress: Int = 0

    lateinit var outerRect: RectF
    lateinit var innerRect: RectF
    lateinit var transparentRect: RectF
    lateinit var textRect: RectF

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(attrs, defStyleAttr)
    }

    fun init(attrs: AttributeSet?, defStyle: Int = 0){
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DonutProgressView)
        val array = context.obtainStyledAttributes(attrs, R.styleable.DonutProgressView, defStyle, 0)
        arcWidth = attributes.getDimension(R.styleable.DonutProgressView_progressArcWidth, 200f)
        defColour = array.getColor(R.styleable.DonutProgressView_defaultArcColour, 0xffffffff.toInt()).toLong()
        arcColour = array.getColor(R.styleable.DonutProgressView_progressArcColour, 0xff000000.toInt()).toLong()
        textColour = array.getColor(R.styleable.DonutProgressView_progressTextColour, 0xff000000.toInt()).toLong()
        textPadding = attributes.getDimension(R.styleable.DonutProgressView_progressTextPadding, 20f)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mWidth = width.toFloat()
        mHeight = height.toFloat()

        if(mHeight != mWidth){
            mHeight = min(width.toFloat(), height.toFloat())
            mWidth = min(width.toFloat(), height.toFloat())
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        setLayerType(LAYER_TYPE_SOFTWARE, null)

        outerRect = RectF(paddingStart.toFloat(),
            paddingTop.toFloat(),
            mWidth - paddingEnd.toFloat(),
            mHeight - paddingBottom.toFloat())

        innerRect = RectF(paddingStart.toFloat() + 20,
            paddingTop.toFloat() + 20,
            mWidth - paddingEnd.toFloat() - 20,
            mHeight - paddingBottom.toFloat() - 20)

        textRect = RectF(paddingStart.toFloat() + arcWidth ,
            paddingTop.toFloat() + arcWidth ,
            mWidth - paddingEnd.toFloat() - arcWidth ,
            mHeight - paddingBottom.toFloat() - arcWidth )

        val paintDefault = TextPaint(TextPaint.ANTI_ALIAS_FLAG)
        paintDefault.color = defColour.toInt()

        val paintProgress = TextPaint(TextPaint.ANTI_ALIAS_FLAG)
        paintProgress.color = arcColour.toInt()

        canvas?.drawArc(innerRect, 270f, 360f, true, paintDefault)
        canvas?.drawArc(innerRect, 270f, -(pFactor * mProgress), true, paintProgress)

        val radius = min(mWidth/2, mHeight/2) - arcWidth

        //p.setColor(getResources().getColor(android.R.color.transparent))
        val p = Paint()
        p.setXfermode(PorterDuffXfermode(PorterDuff.Mode.CLEAR))
        canvas?.drawCircle(mWidth/2, mHeight/2, radius, p )

        val largeTextHeight = (mHeight - 2 * arcWidth)/2 - textPadding
        val textPaintTitle = TextPaint(TextPaint.ANTI_ALIAS_FLAG)//The Paint that will draw the text
        textPaintTitle.color = textColour.toInt()

        textPaintTitle.style = Paint.Style.FILL
        textPaintTitle.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        //textPaintTitle.typeface = ResourcesCompat.getFont(context, R.font.rubik_medium)
        textPaintTitle.isAntiAlias = true
        textPaintTitle.textSize = largeTextHeight
        textPaintTitle.isSubpixelText = true
        textPaintTitle.textAlign = Paint.Align.CENTER
        textPaintTitle.isLinearText = true

        canvas?.drawText("${mProgress}", mWidth/2, mHeight/2 + largeTextHeight/4, textPaintTitle)
    }

    fun setProgress(progress: Int){
        mProgress = progress
        invalidate()
    }
}