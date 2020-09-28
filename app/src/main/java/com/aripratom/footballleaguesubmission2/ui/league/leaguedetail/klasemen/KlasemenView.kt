package com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.klasemen

import com.aripratom.footballleaguesubmission2.ui.model.Klasemen

interface KlasemenView {
    fun showLoading()
    fun showKlasemen(data: List<Klasemen>)
    fun hideLoading()
}