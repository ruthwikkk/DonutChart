package com.ruthwikkk.chartview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ruthwikkk.chartview.databinding.FragmentProgressChartBinding
import kotlinx.android.synthetic.main.fragment_progress_chart.*

class ProgressChartFragment: Fragment(), ProgressTimer.LiveQuizTimerListener  {

    lateinit var binding: FragmentProgressChartBinding
    var timer: ProgressTimer = ProgressTimer()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentProgressChartBinding.inflate(inflater, container, false).apply {

        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initViews()
        timer.addListener(this)
    }

    private fun initViews(){
        progress.setOnClickListener {

        }

        btn_progress_timer_start.setOnClickListener {
            timer.purge()
            timer.start()
        }
    }

    override fun LiveQuizTimerCompleted() {

    }

    override fun LiveQuizTimerTick(timeRemaining: Long) {
        progress.setProgress(getPercentage(10, timeRemaining.toInt()))
    }

    private fun getPercentage(total: Int, value: Int): Int {
        return if (total > 0 && value > 0) {
            value * 100 / total
        } else 0
    }
}