package aaronhammer.ceg4110homework2

import ModelClock
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.TextView

class MyDigitalClock : TextView {
    internal var  mClock: ModelClock? = null

    private var mTicker: Runnable? = null
    private var mHandler: Handler? = null

    constructor(context: Context) : super(context) {
        initClock()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initClock()
    }

    private fun initClock() {
        if (mClock == null) {
            mClock = ModelClock()
        }
    }

    fun setClock(clock: ModelClock) {
        mClock?.destroy()
        mClock = clock
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        mHandler = Handler()

        /**
         * requests a tick on the next hard-second boundary
         */
        mTicker = Runnable {
            text = mClock?.calender?.time.toString()
            invalidate()
            mHandler?.postDelayed(mTicker, 100)
        }
        mTicker!!.run()
    }
}