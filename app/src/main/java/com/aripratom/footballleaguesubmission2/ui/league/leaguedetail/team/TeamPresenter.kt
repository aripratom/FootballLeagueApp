package com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.team

import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.api.TheSportDBApi
import com.aripratom.footballleaguesubmission2.ui.model.TeamResponse
import com.aripratom.footballleaguesubmission2.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(private val view: TeamView, private val apiRepository: ApiRepository, private val gson: Gson
                    , private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(leagueName : String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeam(leagueName)).await(),
                TeamResponse::class.java
            )
            if (data.team != null){
                view.hideLoading()
                view.showTeamList(data.team)

            }
        }
    }

    fun getTeamSearch(team : String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeamSearch(team)).await(),
                TeamResponse::class.java
            )

            if (data.team != null) {
                val filter = data.team.filter { it.Soccer == "Soccer" }
                view.showTeamList(filter)
            }
        }
    }
}