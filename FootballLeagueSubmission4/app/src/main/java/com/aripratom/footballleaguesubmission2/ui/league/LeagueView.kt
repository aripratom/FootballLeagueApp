package com.aripratom.footballleaguesubmission2.ui.league

import com.aripratom.footballleaguesubmission2.ui.model.League

interface LeagueView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: List<League>)


}