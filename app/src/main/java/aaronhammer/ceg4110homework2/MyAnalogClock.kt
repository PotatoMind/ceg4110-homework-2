package aaronhammer.ceg4110homework2

import ModelClock
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import java.util.*


class MyAnalogClock : View {    /** height, width of the clock's view  */
    private var mHeight: Int = 0
    private var mWidth: Int = 0

    /** numeric numbers to denote the hours  */
    private val mClockHours = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

    /** spacing and padding of the clock-hands around the clock round  */
    private var mPadding = 0
    private val mNumeralSpacing = 0

    /** truncation of the heights of the clock-hands,
     * hour clock-hand will be smaller comparetively to others  */
    private var mHandTruncation: Int = 0
    private var mHourHandTruncation: Int = 0

    /** others attributes to calculate the locations of hour-points  */
    private var mRadius = 0
    private var mPaint: Paint? = null
    private val mRect = Rect()
    private var isInit: Boolean = false  // it will be true once the clock will be initialized.

    private var mClock: ModelClock? = null

    constructor(context: Context) : super(context) {
        initClock()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initClock()
    }

    fun initClock() {
        if(mClock == null) {
            mClock = ModelClock()
        }
    }

    fun setClock(clock: ModelClock) {
        mClock?.destroy()
        mClock = clock
    }

    override fun onDraw(canvas: Canvas) {
        if(!isInit) {
            mPaint = Paint()
            mPadding = mNumeralSpacing + 50  // spacing from the circle border
            mHeight = height
            mWidth = width
            val minAttr = Math.min(mHeight, mWidth)
            mRadius = minAttr / 2 - mPadding

            // for maintaining different heights among the clock-hands
            mHandTruncation = minAttr / 20
            mHourHandTruncation = minAttr / 17
            isInit = true
        }

        /** draw the canvas-color  */
        canvas.drawColor(Color.DKGRAY)

        /** circle border */
        mPaint?.reset()
        mPaint?.color = Color.WHITE
        mPaint?.style = Paint.Style.STROKE
        mPaint?.strokeWidth = 4f
        mPaint?.isAntiAlias = true
        canvas.drawCircle(mWidth / 2f, mHeight / 2f, mRadius + mPadding - 10f, mPaint)

        /** clock-center */
        mPaint?.style = Paint.Style.FILL;
        canvas.drawCircle(mWidth / 2f, mHeight / 2f, 12f, mPaint)  // the 03 clock hands will be rotated from this center point.

        /** border of hours */
        val fontSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14f, resources.displayMetrics).toInt()
        mPaint?.textSize = fontSize.toFloat()  // set font size (optional)

        for (hour in mClockHours) {
            val tmp = hour.toString()
            mPaint?.getTextBounds(tmp, 0, tmp.length, mRect)  // for circle-wise bounding

            // find the circle-wise (x, y) position as mathematical rule
            val angle = Math.PI / 6 * (hour - 3)
            val x = (mWidth / 2f + Math.cos(angle) * mRadius - mRect.width() / 2f)
            val y = (mHeight / 2f + Math.sin(angle) * mRadius + mRect.height() / 2f)

            canvas.drawText(hour.toString(), x.toFloat(), y.toFloat(), mPaint)  // you can draw dots to denote hours as alternative
        }

        var hour = mClock!!.calender.get(GregorianCalendar.HOUR_OF_DAY)
        hour = if (hour > 12) hour - 12 else hour

        drawHandLine(canvas, (hour + mClock!!.calender.get(GregorianCalendar.MINUTE) / 60) * 5.toDouble(), true, false) // draw hours
        drawHandLine(canvas, mClock!!.calender.get(GregorianCalendar.MINUTE).toDouble(), false, false) // draw minutes
        drawHandLine(canvas, mClock!!.calender.get(GregorianCalendar.SECOND).toDouble(), false, true) // draw seconds

        /** invalidate the appearance for next representation of time  */
        postInvalidateDelayed(100)
        invalidate()
    }

    private fun drawHandLine(canvas: Canvas, moment: Double, isHour: Boolean, isSecond: Boolean) {
        val angle = Math.PI * moment / 30 - Math.PI / 2
        val handRadius = if (isHour) mRadius - mHandTruncation - mHourHandTruncation else mRadius - mHandTruncation
        if (isSecond) mPaint?.color = Color.YELLOW
        canvas.drawLine(mWidth / 2f, mHeight / 2f, (mWidth / 2 + Math.cos(angle) * handRadius).toFloat(), (mHeight / 2 + Math.sin(angle) * handRadius).toFloat(), mPaint)
    }
}