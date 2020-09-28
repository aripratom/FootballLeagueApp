package com.aripratom.footballleaguesubmission2.ui.league

import com.aripratom.footballleaguesubmission2.ui.model.League
import com.aripratom.footballleaguesubmission2.ui.model.TeamItem

interface LeagueView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: List<League>)
    fun showTeamList(data: List<TeamItem>)


}