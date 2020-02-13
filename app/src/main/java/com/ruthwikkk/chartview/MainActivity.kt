package com.ruthwikkk.chartview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        donut_chart.startAnimation()

        donut_chart.setOnClickListener {
            donut_chart.startAnimation()
        }
    }
}
