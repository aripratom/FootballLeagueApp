package com.aripratom.footballleaguesubmission2.ui.api

import android.net.Uri
import com.aripratom.footballleaguesubmission2.BuildConfig

object TheSportDBApi {
    fun getSchedule(scheduleItem: String?, leagueID: String?): String {
            return BuildConfig.BASE_URL + scheduleItem + "?id=" + leagueID
        }

        fun getTeam(league: String?): String {
            return BuildConfig.BASE_URL + "search_all_teams.php?l=" + league
        }

        fun getTeamLogo(teamID: String?): String {
            return BuildConfig.BASE_URL + "lookupteam.php?id=" + teamID
        }


        fun getLeague(league: String?): String {
            return BuildConfig.BASE_URL + "search_all_leagues.php?s=Soccer" + league
        }

        fun getMatchSearch(match: String?): String {
            return BuildConfig.BASE_URL + "searchevents.php?e=" + match
        }

        fun getTeamSearch(team: String?): String {
            return BuildConfig.BASE_URL + "searchteams.php?t=" + team
        }

    }