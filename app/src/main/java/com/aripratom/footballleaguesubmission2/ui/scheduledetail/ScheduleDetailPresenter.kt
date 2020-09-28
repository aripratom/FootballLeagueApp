package com.aripratom.footballleaguesubmission2.ui.scheduledetail

import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.api.TheSportDBApi
import com.aripratom.footballleaguesubmission2.ui.model.TeamResponse
import com.aripratom.footballleaguesubmission2.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ScheduleDetailPresenter(
    private val view: ScheduleDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getHomeLogo(teamID: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeamLogo(teamID)).await(),
                TeamResponse::class.java
            )

            if (data.team != null) {
                view.showHomeLogo(data.team)

            }
        }
    }

    fun getAwayLogo(teamID: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeamLogo(teamID)).await(),
                TeamResponse::class.java
            )

            if (data.team != null) {
                view.showAwayLogo(data.team)
            }

        }
    }

}