package com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.klasemen

import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.api.TheSportDBApi
import com.aripratom.footballleaguesubmission2.ui.model.KlasemenResponse
import com.aripratom.footballleaguesubmission2.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_klasemen.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class KlasemenPresenter(
    private val view: KlasemenView, private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getKlasemen(leagueId: String) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getKlasemen(leagueId)).await(),
                KlasemenResponse::class.java
            )


                try {
                    if (data.table.isNotEmpty()) {
                        view.showKlasemen(data.table)
                        view.hideLoading()
                    }
                }
                catch(ex:Exception){
                    ex.printStackTrace()
                }



        }
    }
}