package com.aripratom.footballleaguesubmission2.ui.schedule

import com.aripratom.footballleaguesubmission2.ui.api.ApiRespository
import com.aripratom.footballleaguesubmission2.ui.api.TheSportDBApi
import com.aripratom.footballleaguesubmission2.ui.model.League
import com.aripratom.footballleaguesubmission2.ui.model.LeagueResponse
import com.aripratom.footballleaguesubmission2.ui.model.ScheduleResponse
import com.aripratom.footballleaguesubmission2.ui.model.ScheduleSearchResponse
import com.aripratom.footballleaguesubmission2.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SchedulePresenter(
    private val view: ScheduleView,
    private val apiRespository: ApiRespository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeagueList(league: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRespository.doRequest(TheSportDBApi.getLeague(league)).await(),
                LeagueResponse::class.java
            )
            if (data.leagues != null){
                view.showLeagueList(data.leagues)
                view.hideLoading()
            }
        }

    }

    fun getScheduleList(scheduleItem: String?, leagueID: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRespository.doRequest(TheSportDBApi.getSchedule(scheduleItem, leagueID)).await(),
                ScheduleResponse::class.java
            )
            //if (data.schedule != null) {
            if (data.schedule.isNotEmpty()) {
                view.showScheduleList(data.schedule)
                view.hideLoading()
            }
        }
    }

    fun getScheduleSearch(keyword: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRespository.doRequest(TheSportDBApi.getMatchSearch(keyword)).await(),
                ScheduleSearchResponse::class.java
            )
            //if (data.schedule != null) {
            if (data.schedule.isNotEmpty()) {
                val filter = data.schedule.filter { it.Soccer == "Soccer" }
                view.showScheduleList(filter)
            }
        }
    }
}


