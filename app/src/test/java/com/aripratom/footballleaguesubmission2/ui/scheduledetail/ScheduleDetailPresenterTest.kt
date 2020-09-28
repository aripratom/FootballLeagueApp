package com.aripratom.footballleaguesubmission2.ui.scheduledetail

import com.aripratom.footballleaguesubmission2.TestContextProvider
import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.ui.model.TeamItem
import com.aripratom.footballleaguesubmission2.ui.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ScheduleDetailPresenterTest {
    @Mock
    private lateinit var view: ScheduleDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRespository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: ScheduleDetailPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ScheduleDetailPresenter(view, apiRespository, gson, TestContextProvider())
    }

    @Test
    fun getHomeLogo() {
        val data: MutableList<TeamItem> = mutableListOf()
        val response = TeamResponse(data)
        val key = "4406"

        runBlocking {
            Mockito.`when`(apiRespository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", TeamResponse::class.java))
                .thenReturn(response)

            presenter.getHomeLogo(key)

            Mockito.verify(view).showHomeLogo(data)

        }
    }

    @Test
    fun getAwayLogo() {
        val data: MutableList<TeamItem> = mutableListOf()
        val response = TeamResponse(data)
        val key = "133602"

        runBlocking {
            Mockito.`when`(apiRespository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", TeamResponse::class.java))
                .thenReturn(response)

            presenter.getAwayLogo(key)

            Mockito.verify(view).showAwayLogo(data)

        }
    }
}