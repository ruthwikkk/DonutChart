package com.ruthwikkk.chartview

import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(DonutChartFragment(),
            R.id.home_container, home_container)

        bottom_navigation.setOnNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener,
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(menu: MenuItem): Boolean {
                when(menu.itemId){
                    R.id.action_donut -> {
                        loadFragment(DonutChartFragment(),
                            R.id.home_container, home_container)
                    }
                    R.id.action_progress -> {
                        loadFragment(ProgressChartFragment(),
                            R.id.home_container, home_container)
                    }
                }

                return true
            }
        })
    }

    fun loadFragment(fragment: Fragment, containerViewId : Int, containerView : ViewGroup) {
        try {
            containerView.removeAllViews()
            var transaction: androidx.fragment.app.FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction = transaction.replace(containerViewId, fragment)
            transaction.commit()
        } catch (e: Exception) {

        }

    }
}
