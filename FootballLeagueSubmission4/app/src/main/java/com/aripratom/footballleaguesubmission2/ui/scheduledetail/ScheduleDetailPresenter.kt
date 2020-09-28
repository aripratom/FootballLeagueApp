package com.aripratom.footballleaguesubmission2.ui.scheduledetail

import com.aripratom.footballleaguesubmission2.ui.api.ApiRespository
import com.aripratom.footballleaguesubmission2.ui.api.TheSportDBApi
import com.aripratom.footballleaguesubmission2.ui.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ScheduleDetailPresenter(private val view: ScheduleDetailView, private val apiRepository: ApiRespository, private val gson: Gson){

    fun getHomeLogo(teamID: String?){
        GlobalScope.launch {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamLogo(teamID)).await(), TeamResponse::class.java)

                if (data.team != null) {
                    view.showHomeLogo(data.team)

            }
        }
    }

    fun getAwayLogo(teamID: String?){
        GlobalScope.launch {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamLogo(teamID)).await(), TeamResponse::class.java)

                if (data.team != null) {
                    view.showAwayLogo(data.team)
                }

        }
    }

}