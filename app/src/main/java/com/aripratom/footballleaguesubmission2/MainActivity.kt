package com.aripratom.footballleaguesubmission2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aripratom.footballleaguesubmission2.ui.favorite.FavoriteFragment
import com.aripratom.footballleaguesubmission2.ui.league.LeagueFragment
import com.aripratom.footballleaguesubmission2.ui.schedule.ScheduleFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        nav_view.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.navigation_league -> {
                    loadFragment(savedInstanceState, LeagueFragment())
                }
                R.id.navigation_schedule -> {
                    loadFragment(savedInstanceState,ScheduleFragment())
                }
                R.id.navigation_favorite ->{
                    loadFragment(savedInstanceState, FavoriteFragment())
                }
            }
            true
        }
        nav_view.selectedItemId = R.id.navigation_schedule


    }

    private fun loadFragment(savedInstanceState: Bundle?, fragment: Fragment) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment, fragment::class.java.simpleName)
                .commit()
        }
    }



}
