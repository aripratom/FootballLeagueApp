package com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.team.player

import android.util.Log
import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.api.TheSportDBApi
import com.aripratom.footballleaguesubmission2.ui.model.PlayerResponse
import com.aripratom.footballleaguesubmission2.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(
    private val view: PlayerView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getPlayerData(teamName: String?) {

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getPlayer(teamName)).await(),
                PlayerResponse::class.java
            )

            if (data.player != null) {
                view.showPlayerList(data.player)
                Log.d("PLAYER","${data.player}")
            }
        }

    }
}