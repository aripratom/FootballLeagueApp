package com.aripratom.footballleaguesubmission2.ui.league

import android.util.Log
import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.api.TheSportDBApi
import com.aripratom.footballleaguesubmission2.ui.model.LeagueResponse
import com.aripratom.footballleaguesubmission2.util.CoroutineContextProvider
import com.aripratom.footballleaguesubmission2.util.EspressoIdlingResource
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeaguePresenter(
    private val view: LeagueView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLeagueList(league: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getLeague(league)).await(),
                LeagueResponse::class.java
            )

            if (data.leagues != null){
                view.showLeagueList(data.leagues)
            }
            view.hideLoading()

        }

    }
}