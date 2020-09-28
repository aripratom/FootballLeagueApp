package com.aripratom.footballleaguesubmission2.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.model.League
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_league_detail.*

class LeagueDetailActivity : AppCompatActivity() {

    private lateinit var league: League

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)

        supportActionBar?.title = getString(R.string.league_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        league = intent.getParcelableExtra("league")
        getDetails(league)

    }

    private fun getDetails(league: League) {
        LeagueNameDetail.text = league.leagueName
        LeagueDesc.text = league.leagueDesc

        Picasso.get().load(league.leagueBadge).into(LeagueLogoDetail)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

}
