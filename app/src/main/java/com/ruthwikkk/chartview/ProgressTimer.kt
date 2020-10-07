package com.ruthwikkk.chartview

import android.os.Handler

class ProgressTimer: Runnable {

    interface LiveQuizTimerListener{
        fun LiveQuizTimerCompleted()
        fun LiveQuizTimerTick(timeRemaining: Long)
    }

    private var handler: Handler? = null
    private var listener: LiveQuizTimerListener? = null
    private var timeElapsed = 0
    private var timerPausedAt = -1

    val VIDEO_NEXT_ITEM_WAIT = 10L

    fun addListener(callback: LiveQuizTimerListener): ProgressTimer{
        purge()
        this.listener = callback
        return this
    }

    fun start() {
        handler = Handler()
        purge()
        handler?.post(this)
    }

    override fun run() {
        val timeRem = VIDEO_NEXT_ITEM_WAIT - timeElapsed
        listener?.LiveQuizTimerTick(timeRem)
        if(timeElapsed > VIDEO_NEXT_ITEM_WAIT){
            listener?.LiveQuizTimerCompleted()
            purge()
            return
        }
        timeElapsed++
        handler!!.postDelayed(this, 1000)
    }

    fun purge(){
        handler?.removeCallbacks(this)
        timeElapsed = 0
    }

    fun resume(){
        if(timerPausedAt <= VIDEO_NEXT_ITEM_WAIT){
            handler = Handler()
            purge()
            timeElapsed = (VIDEO_NEXT_ITEM_WAIT - timerPausedAt).toInt()
            handler?.post(this)
        }
    }

    fun pause(){
        timerPausedAt = (VIDEO_NEXT_ITEM_WAIT - timeElapsed).toInt()
        purge()
    }

    fun isPaused(): Boolean = timerPausedAt > 0
}