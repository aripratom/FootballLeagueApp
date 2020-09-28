package com.aripratom.footballleaguesubmission2.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.klasemen.KlasemenFragment
import com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.overview.LeagueOverviewFragment
import com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.team.TeamFragment
import com.aripratom.footballleaguesubmission2.ui.model.League
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_league_detail.*

class LeagueDetailActivity : AppCompatActivity() {
    private lateinit var league: League
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)

        supportActionBar?.title = getString(R.string.league_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        league = intent.getParcelableExtra("league")

        //VIEW PAGER
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, league)

        viewpagerLeague.adapter = mSectionsPagerAdapter

        viewpagerLeague.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabsLeagueDetail))
        tabsLeagueDetail.addOnTabSelectedListener(
            TabLayout.ViewPagerOnTabSelectedListener(
                viewpagerLeague
            )
        )
    }


    inner class SectionsPagerAdapter(fm: FragmentManager, val league: League?) :
        FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            val fragment: Fragment
            when (position) {
                0 -> {
                    fragment =
                        TeamFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("league", league)
                    fragment.arguments = bundle
                    return fragment
                }
                1 -> {

                    fragment =
                        KlasemenFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("league", league)
                    fragment.arguments = bundle
                    return fragment
                }

                2 -> {
                    fragment = LeagueOverviewFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("league", league)
                    fragment.arguments = bundle
                    return fragment
                }
            }
            return TeamFragment()
        }

        override fun getCount(): Int {
            return 3
        }
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
