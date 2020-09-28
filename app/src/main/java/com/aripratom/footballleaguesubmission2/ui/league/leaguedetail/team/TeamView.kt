package com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.team

import com.aripratom.footballleaguesubmission2.ui.model.TeamItem

interface TeamView {

    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<TeamItem>)

}