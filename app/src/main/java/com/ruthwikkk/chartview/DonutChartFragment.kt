package com.ruthwikkk.chartview

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ruthwikkk.chartview.databinding.FragmentDonutChartBinding
import kotlinx.android.synthetic.main.fragment_donut_chart.*

class DonutChartFragment: Fragment() {

    lateinit var binding: FragmentDonutChartBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentDonutChartBinding.inflate(inflater, container, false).apply {

        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initViews()
        Handler().postDelayed({
            donut_chart.animate().alpha(1f).setDuration(300).start()
            donut_chart.startAnimation()
        }, 1000)
    }

    private fun initViews(){
        donut_chart.setOnClickListener {
            donut_chart.startAnimation()
        }
    }
}