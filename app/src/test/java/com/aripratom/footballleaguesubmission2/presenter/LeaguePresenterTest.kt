package com.aripratom.footballleaguesubmission2.presenter

import com.aripratom.footballleaguesubmission2.TestContextProvider
import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.ui.league.LeaguePresenter
import com.aripratom.footballleaguesubmission2.ui.league.LeagueView
import com.aripratom.footballleaguesubmission2.ui.model.League
import com.aripratom.footballleaguesubmission2.ui.model.LeagueResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeaguePresenterTest {
    @Mock
    private lateinit var view: LeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: LeaguePresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LeaguePresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getLeagueList() {
        val data: MutableList<League> = mutableListOf()
        val response = LeagueResponse(data)
        val key = "4406"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", LeagueResponse::class.java))
                .thenReturn(response)

            presenter.getLeagueList(key)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLeagueList(data)
            Mockito.verify(view).hideLoading()

        }
    }
}