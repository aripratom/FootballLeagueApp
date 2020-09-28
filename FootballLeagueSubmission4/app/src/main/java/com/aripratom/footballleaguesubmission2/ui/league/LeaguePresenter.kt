package com.aripratom.footballleaguesubmission2.ui.league

import com.aripratom.footballleaguesubmission2.ui.api.ApiRespository
import com.aripratom.footballleaguesubmission2.ui.api.TheSportDBApi
import com.aripratom.footballleaguesubmission2.ui.model.LeagueResponse
import com.aripratom.footballleaguesubmission2.util.CoroutineContextProvider
import com.aripratom.footballleaguesubmission2.util.EspressoIdlingResource
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeaguePresenter(
    private val view: LeagueView,
    private val apiRespository: ApiRespository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLeagueList(league: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
          //  EspressoIdlingResource.increment()
            val data = gson.fromJson(
                apiRespository.doRequest(TheSportDBApi.getLeague(league)).await(),
                LeagueResponse::class.java
            )

            //if (data.leagues != null)
            if(data.leagues.isNotEmpty()){
            //    EspressoIdlingResource.decrement()
                view.showLeagueList(data.leagues)
                view.hideLoading()
            }
        }

    }
}