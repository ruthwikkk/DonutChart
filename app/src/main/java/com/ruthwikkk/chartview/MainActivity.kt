package com.ruthwikkk.chartview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LiveQuizTimer.LiveQuizTimerListener {

    var timer: LiveQuizTimer = LiveQuizTimer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*donut_chart.startAnimation()

        donut_chart.setOnClickListener {
            donut_chart.startAnimation()
        }*/
        timer.addListener(this)

        progress.setOnClickListener {
            timer.start()
        }

        progress.setProgress(30)
    }

    override fun LiveQuizTimerCompleted() {

    }

    override fun LiveQuizTimerTick(timeRemaining: Long) {
        progress.setProgress(getPercentage(10,timeRemaining.toInt()))
    }

    fun getPercentage(total: Int, value: Int): Int {
        return if (total > 0 && value > 0) {
            value * 100 / total
        } else 0
    }
}
