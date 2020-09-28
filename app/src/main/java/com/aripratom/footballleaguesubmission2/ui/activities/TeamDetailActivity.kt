package com.aripratom.footballleaguesubmission2.ui.activities

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.database.database
import com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.overview.TeamOverviewFragment
import com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.team.player.PlayerFragment
import com.aripratom.footballleaguesubmission2.ui.model.TeamItem
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class TeamDetailActivity : AppCompatActivity() {

    private lateinit var team : TeamItem
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        supportActionBar?.title = getString(R.string.team_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        team = intent.getParcelableExtra("team")

        id = team.teamID+""

        favoriteState()

        //VIEW PAGER
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, team)

        viewpagerTeam.adapter = mSectionsPagerAdapter

        viewpagerTeam.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabsTeamDetail))
        tabsTeamDetail.addOnTabSelectedListener(
            TabLayout.ViewPagerOnTabSelectedListener(
                viewpagerTeam
            )
        )
    }

    inner class SectionsPagerAdapter(fm: FragmentManager, val team : TeamItem?) :
        FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            val fragment: Fragment
            when (position) {
                0 -> {
                    fragment =
                        PlayerFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("team", team)
                    fragment.arguments = bundle
                    return fragment
                }
                1 -> {

                    fragment =
                        TeamOverviewFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("team", team)
                    fragment.arguments = bundle
                    return fragment
                }


            }
            return PlayerFragment()
        }

        override fun getCount(): Int {
            return 2
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(TeamItem.TABLE_TEAM,
                    TeamItem.ID to team.id,
                    TeamItem.TEAM_ID to team.teamID,
                    TeamItem.TEAM_NAME to team.teamName,
                    TeamItem.FORMED_YEAR to team.formedYear,
                    TeamItem.STADIUM to team.teamStadium,
                    TeamItem.DESCIPTION to team.teamDesc,
                    TeamItem.BADGE to team.teamBadge,
                    TeamItem.SOCCER to team.Soccer)


            }
            snackbar(tabsTeamDetail, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(tabsTeamDetail, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(TeamItem.TABLE_TEAM, "(TEAM_ID = {teamID})", "teamID" to id)
            }
            snackbar(tabsTeamDetail, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(tabsTeamDetail, e.localizedMessage).show()
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(TeamItem.TABLE_TEAM).whereArgs("(TEAM_ID = {teamID})", "teamID" to id)
            val favorite = result.parseList(classParser<TeamItem>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}
