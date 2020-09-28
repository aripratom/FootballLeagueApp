package com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.team.player

import com.aripratom.footballleaguesubmission2.ui.model.Player

interface PlayerView {
    fun showPlayerList(data: List<Player>)
}