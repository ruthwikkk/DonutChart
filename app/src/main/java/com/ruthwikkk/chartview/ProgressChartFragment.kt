package com.ruthwikkk.chartview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ruthwikkk.chartview.databinding.FragmentProgressChartBinding
import kotlinx.android.synthetic.main.fragment_progress_chart.*

class ProgressChartFragment: Fragment(), LiveQuizTimer.LiveQuizTimerListener  {

    lateinit var binding: FragmentProgressChartBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentProgressChartBinding.inflate(inflater, container, false).apply {

        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

    private fun initViews(){
        progress.setOnClickListener {

        }
    }

    override fun LiveQuizTimerCompleted() {

    }

    override fun LiveQuizTimerTick(timeRemaining: Long) {

    }
}