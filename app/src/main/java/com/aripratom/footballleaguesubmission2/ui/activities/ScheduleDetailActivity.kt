package com.aripratom.footballleaguesubmission2.ui.activities

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.database.database
import com.aripratom.footballleaguesubmission2.ui.model.ScheduleItem
import com.aripratom.footballleaguesubmission2.ui.model.TeamItem
import com.aripratom.footballleaguesubmission2.ui.scheduledetail.ScheduleDetailPresenter
import com.aripratom.footballleaguesubmission2.ui.scheduledetail.ScheduleDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_schedule_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import java.text.SimpleDateFormat
import java.util.*

class ScheduleDetailActivity : AppCompatActivity(), ScheduleDetailView {

    private lateinit var schedule: ScheduleItem
    private lateinit var presenter: ScheduleDetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String


    override fun showHomeLogo(schedule: List<TeamItem>) {
        Picasso.get().load(schedule[0].teamBadge).into(imgHomeTeamDetail)
    }

    override fun showAwayLogo(schedule: List<TeamItem>) {
        Picasso.get().load(schedule[0].teamBadge).into(imgAwayTeamDetail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_detail)

        supportActionBar?.title = getString(R.string.detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        schedule = intent.getParcelableExtra("schedule")
        id = schedule.eventId+""

        getDetail(schedule)

        favoriteState()

        val apiRespository = ApiRepository()
        val gson = Gson()

        presenter = ScheduleDetailPresenter(this, apiRespository, gson)
        presenter.run {
            getHomeLogo(schedule.homeTeamID)
            getAwayLogo(schedule.awayTeamID)
        }
    }

    private fun getDetail(schedule: ScheduleItem) {
        if (schedule.matchDate != null) {
            val format = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
            val sdf = SimpleDateFormat("E, dd MMMM yyyy", Locale.getDefault())
            val date: String = sdf.format(format.parse(schedule.matchDate))

            tvDateDetail.text = date
        }
        tvHomeTeamDetail.text = schedule.homeTeam
        tvAwayTeamDetail.text = schedule.awayTeam
        tvHomeGoal.text = schedule.homeScorer
        tvAwayGoal.text = schedule.awayScorer
        tvHomeScore.text = schedule.homeTeamScore
        tvAwayScore.text = schedule.awayTeamScore
        tvHomeShot.text = schedule.homeShot
        tvAwayShot.text = schedule.awayShot
        tvHomeGK.text = schedule.homeGK
        tvAwayGK.text = schedule.awayGK
        tvHomeDF.text = schedule.homeDF
        tvAwayDF.text = schedule.awayDF
        tvHomeMF.text = schedule.homeMF
        tvAwayMF.text = schedule.awayMF
        tvHomeFW.text = schedule.homeFW
        tvAwayFW.text = schedule.awayFW
        tvHomeSub.text = schedule.homeSubs
        tvAwaySub.text = schedule.awaySubs

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(ScheduleItem.TABLE_FAVORITE,
                    ScheduleItem.ID to schedule.id,
                    ScheduleItem.EVENT_ID to schedule.eventId,
                    ScheduleItem.MATCH_DATE to schedule.matchDate,
                    ScheduleItem.HOME_TEAM to schedule.homeTeam,
                    ScheduleItem.AWAY_TEAM to schedule.awayTeam,
                    ScheduleItem.HOME_SCORE to schedule.homeTeamScore,
                    ScheduleItem.AWAY_SCORE to schedule.awayTeamScore,
                    ScheduleItem.HOME_SCORER to schedule.homeScorer,
                    ScheduleItem.AWAY_SCORER to schedule.awayScorer,
                    ScheduleItem.HOME_SHOT to schedule.homeShot,
                    ScheduleItem.AWAY_SHOT to schedule.awayShot,
                    ScheduleItem.HOME_GK to schedule.homeGK,
                    ScheduleItem.AWAY_GK to schedule.awayGK,
                    ScheduleItem.HOME_DF to schedule.homeDF,
                    ScheduleItem.AWAY_DF to schedule.awayDF,
                    ScheduleItem.HOME_MF to schedule.homeMF,
                    ScheduleItem.AWAY_MF to schedule.awayMF,
                    ScheduleItem.HOME_FW to schedule.homeFW,
                    ScheduleItem.AWAY_FW to schedule.awayFW,
                    ScheduleItem.HOME_SUB to schedule.homeSubs,
                    ScheduleItem.AWAY_SUB to schedule.awaySubs,
                    ScheduleItem.TEAM_BADGE to schedule.teamBadge,
                    ScheduleItem.HOME_ID to schedule.homeTeamID,
                    ScheduleItem.AWAY_ID to schedule.awayTeamID,
                    ScheduleItem.SOCCER to schedule.Soccer)
            }
            snackbar(tvSub, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(tvSub, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    ScheduleItem.TABLE_FAVORITE, "(EVENT_ID = {eventId})",
                    "eventId" to id
                )
            }
            snackbar(tvSub,"Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(tvSub,e.localizedMessage).show()
        }
    }
    private fun setFavorite() {

        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(ScheduleItem.TABLE_FAVORITE)
                .whereArgs(
                    "(EVENT_ID = {eventId})",
                    "eventId" to id
                )
            val favorite = result.parseList(classParser<ScheduleItem>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

}
