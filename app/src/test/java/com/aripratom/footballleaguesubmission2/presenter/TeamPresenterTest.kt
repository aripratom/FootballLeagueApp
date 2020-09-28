package com.aripratom.footballleaguesubmission2.presenter

import com.aripratom.footballleaguesubmission2.TestContextProvider
import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.team.TeamPresenter
import com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.team.TeamView
import com.aripratom.footballleaguesubmission2.ui.model.TeamItem
import com.aripratom.footballleaguesubmission2.ui.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamPresenterTest {
    @Mock
    private lateinit var view: TeamView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: TeamPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getTeamList() {
        val data: MutableList<TeamItem> = mutableListOf()
        val response = TeamResponse(data)
        val key = "133604"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", TeamResponse::class.java))
                .thenReturn(response)

            presenter.getTeamList(key)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(data)
            Mockito.verify(view).hideLoading()

        }
    }
}