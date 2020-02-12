package com.ruthwikkk.widget.donutchart

import android.view.animation.Animation
import android.view.animation.Transformation

class AngleProgressAnimation(var chart: DonutChart, private val newProgress: Float) : Animation() {
    private val oldProgress = 0f

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val progress = oldProgress + (newProgress - oldProgress) * interpolatedTime
        chart.angle = progress
        chart.invalidate()
    }
}
