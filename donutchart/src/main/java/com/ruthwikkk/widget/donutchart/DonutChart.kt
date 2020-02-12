package com.ruthwikkk.widget.donutchart

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import kotlin.math.min


class DonutChart: View {

    private var mHeight = 0f
    private var mWidth = 0f

    var angle = 360f
    val pFactor = 3.6f
    var correctPercentage = 75
    var wrongPercentage = 20
    var unPercentage = 5
    var marginAngle = 1f
    var arcWidth = 200f
    var transparentArcWidth = 20f
    var textPadding = 20f

    var A = 0f
    var B = 0f
    var C = 0f
    var M = 0f

    var duration = 1000

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
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DonutChart)
        val array = context.obtainStyledAttributes(attrs, R.styleable.DonutChart, defStyle, 0)
        arcWidth = attributes.getDimension(R.styleable.DonutChart_donutWidth, 200f)
        transparentArcWidth = attributes.getDimension(R.styleable.DonutChart_donutSmallWidth, 20f)
        textPadding = attributes.getDimension(R.styleable.DonutChart_textPadding, 20f)
        correctPercentage = attributes.getInteger(R.styleable.DonutChart_correctPercentage, 33)
        wrongPercentage = attributes.getInteger(R.styleable.DonutChart_wrongPercentage, 33)
        unPercentage = attributes.getInteger(R.styleable.DonutChart_unAnsweredPercentage, 34)
        marginAngle = attributes.getFloat(R.styleable.DonutChart_separatorAngle, 1f)
        duration = attributes.getInteger(R.styleable.DonutChart_animationDuration, 1000)

        A = pFactor*correctPercentage
        B = pFactor*wrongPercentage
        C = pFactor*unPercentage
        M = pFactor*marginAngle/2

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
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        outerRect = RectF(paddingStart.toFloat(),
            paddingTop.toFloat(),
            mWidth - paddingEnd.toFloat(),
            mHeight - paddingBottom.toFloat())

        innerRect = RectF(paddingStart.toFloat() + 20,
            paddingTop.toFloat() + 20,
            mWidth - paddingEnd.toFloat() - 20,
            mHeight - paddingBottom.toFloat() - 20)

        transparentRect = RectF(paddingStart.toFloat() + arcWidth - transparentArcWidth,
            paddingTop.toFloat() + arcWidth - transparentArcWidth,
            mWidth - paddingEnd.toFloat() - arcWidth + transparentArcWidth,
            mHeight - paddingBottom.toFloat() - arcWidth + transparentArcWidth)

        textRect = RectF(paddingStart.toFloat() + arcWidth ,
            paddingTop.toFloat() + arcWidth ,
            mWidth - paddingEnd.toFloat() - arcWidth ,
            mHeight - paddingBottom.toFloat() - arcWidth )

        val p = Paint()

        val paintCorrect = TextPaint(TextPaint.ANTI_ALIAS_FLAG)
        paintCorrect.shader = LinearGradient(
            0f,
            0f,
            0f,
            mHeight,
            ContextCompat.getColor(context, R.color.persian_green),
            ContextCompat.getColor(context, R.color.brand_green),
            Shader.TileMode.MIRROR
        )

        val paintWrong = TextPaint(TextPaint.ANTI_ALIAS_FLAG)
        paintWrong.shader = LinearGradient(
            0f,
            0f,
            0f,
            mHeight,
            ContextCompat.getColor(context, R.color.persian_red),
            ContextCompat.getColor(context, R.color.brand_red),
            Shader.TileMode.MIRROR
        )

        val paintUn = TextPaint(TextPaint.ANTI_ALIAS_FLAG)
        paintUn.shader = LinearGradient(
            0f,
            0f,
            0f,
            mHeight,
            ContextCompat.getColor(context, R.color.bg_like),
            ContextCompat.getColor(context, R.color.bg_like),
            Shader.TileMode.MIRROR
        )

        val paintTrasparent = TextPaint(TextPaint.ANTI_ALIAS_FLAG)
        paintTrasparent.color = ContextCompat.getColor(context, R.color.white_transparent)


        if(angle > 0 && angle <= M){
            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, 0f, angle, true, p)

        }else if(angle > M && angle <= A - M){

            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, 0f, M, true, p)

            canvas?.drawArc(outerRect, M, angle - M, true, paintCorrect)
            canvas?.drawArc(transparentRect, M, angle - M, true, paintTrasparent)

        }else if(angle > A - M && angle <= A + M){
            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, 0f, M, true, p)


            canvas?.drawArc(outerRect, M, A - M , true, paintCorrect)
            canvas?.drawArc(transparentRect, M, A - M, true, paintTrasparent)

            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, A - M, angle - (A-M), true, p)

        }else if(angle > A + M && angle <= A+B-M){
            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, 0f, M, true, p)


            canvas?.drawArc(outerRect, M, A - M , true, paintCorrect)
            canvas?.drawArc(transparentRect, M, A - M, true, paintTrasparent)

            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, A - M, 2 * M, true, p)


            canvas?.drawArc(innerRect, A + M, angle - (A+M), true, paintWrong)
            canvas?.drawArc(transparentRect, A + M, angle - (A+M), true, paintTrasparent)

        }else if(angle > A+B-M && angle <= A+B+M){
            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, 0f, M, true, p)


            canvas?.drawArc(outerRect, M, A - M , true, paintCorrect)
            canvas?.drawArc(transparentRect, M, A - M, true, paintTrasparent)

            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, A - M, 2 * M, true, p)


            canvas?.drawArc(innerRect, A + M, B - 2*M, true, paintWrong)
            canvas?.drawArc(transparentRect, A + M, B - 2*M, true, paintTrasparent)

            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, A + B - M, angle - (A + B - M), true, p)

        }else if(angle > A+B+M && angle <= A+B+C-M){
            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, 0f, M, true, p)


            canvas?.drawArc(outerRect, M, A - M , true, paintCorrect)
            canvas?.drawArc(transparentRect, M, A - M, true, paintTrasparent)

            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, A - M, 2 * M, true, p)


            canvas?.drawArc(innerRect, A + M, B - 2*M, true, paintWrong)
            canvas?.drawArc(transparentRect, A + M, B - 2*M, true, paintTrasparent)

            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, A + B - M, 2 * M, true, p)

            canvas!!.drawArc(innerRect, A + B + M, angle - (A + B + M), true, paintUn)
            canvas!!.drawArc(transparentRect, A + B + M, angle - (A + B + M), true, paintTrasparent)

        }else if(angle > A+B+C-M && angle <= 360){
            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, 0f, M, true, p)


            canvas?.drawArc(outerRect, M, A - M , true, paintCorrect)
            canvas?.drawArc(transparentRect, M, A - M, true, paintTrasparent)

            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, A - M, 2 * M, true, p)


            canvas?.drawArc(innerRect, A + M, B - 2*M, true, paintWrong)
            canvas?.drawArc(transparentRect, A + M, B - 2*M, true, paintTrasparent)

            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, A + B - M, 2 * M, true, p)

            canvas!!.drawArc(innerRect, A + B + M, C- 2 * M, true, paintUn)
            canvas!!.drawArc(transparentRect, A + B + M, C- 2 * M, true, paintTrasparent)

            p.setColor(Color.parseColor("#00FFFFFF"))
            canvas!!.drawArc(outerRect, A+B+C-M, M, true, p)
        }

        val centerX = getMeasuredWidth()/ 2;
        val centerY = getMeasuredHeight()/ 2;
        val radius = min(mWidth/2, mHeight/2) - arcWidth

        //p.setColor(getResources().getColor(android.R.color.transparent))
        p.setXfermode(PorterDuffXfermode(PorterDuff.Mode.CLEAR))
        canvas?.drawCircle(mWidth/2, mHeight/2, radius, p )

        val largeTextHeight = (mHeight - 2 * arcWidth)/2 - textPadding
        val smallTextHeight = largeTextHeight/2
        val textPaintTitle = TextPaint(TextPaint.ANTI_ALIAS_FLAG)//The Paint that will draw the text
        textPaintTitle.shader = LinearGradient(
            0f,
            0f,
            0f,
            mHeight,
            ContextCompat.getColor(context, R.color.persian_green),
            ContextCompat.getColor(context, R.color.brand_green),
            Shader.TileMode.MIRROR
        )//Change the color if your background is white!
        textPaintTitle.style = Paint.Style.FILL
        textPaintTitle.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        //textPaintTitle.typeface = ResourcesCompat.getFont(context, R.font.rubik_medium)
        textPaintTitle.isAntiAlias = true
        textPaintTitle.textSize = largeTextHeight
        textPaintTitle.isSubpixelText = true
        textPaintTitle.textAlign = Paint.Align.CENTER
        textPaintTitle.isLinearText = true

        canvas?.drawText("${correctPercentage}%", mWidth/2, mHeight/2 + largeTextHeight/2 - smallTextHeight/2, textPaintTitle)

        textPaintTitle.textSize = smallTextHeight
        canvas?.drawText("Correct", mWidth/2, mHeight/2 + largeTextHeight/2 + smallTextHeight/2, textPaintTitle)
    }

    fun startAnimation() {
        val lineProgressAnimation = AngleProgressAnimation(this, angle)
        lineProgressAnimation.setDuration(duration.toLong())
        super.startAnimation(lineProgressAnimation)
    }
}