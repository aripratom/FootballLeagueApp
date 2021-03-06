package com.aripratom.footballleaguesubmission2.presenter

import com.aripratom.footballleaguesubmission2.TestContextProvider
import com.aripratom.footballleaguesubmission2.ui.api.ApiRespository
import com.aripratom.footballleaguesubmission2.ui.model.*
import com.aripratom.footballleaguesubmission2.ui.schedule.SchedulePresenter
import com.aripratom.footballleaguesubmission2.ui.schedule.ScheduleView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SchedulePresenterTest {
    @Mock
    private lateinit var view: ScheduleView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRespository: ApiRespository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: SchedulePresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SchedulePresenter(view, apiRespository, gson, TestContextProvider())
    }

   @Test
    fun getLeagueList() {
        val data: MutableList<League> = mutableListOf()
        val response = LeagueResponse(data)
        val key = "4406"

        runBlocking {
            Mockito.`when`(apiRespository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", LeagueResponse::class.java))
                .thenReturn(response)

            presenter.getLeagueList(key)

            Mockito.verify(view).showLoading()
            view.showLeagueList(data)
            Mockito.verify(view).showLeagueList(data)
            view.hideLoading()
            Mockito.verify(view).hideLoading()

        }
    }

    @Test
    fun getScheduleList(){
        val data: MutableList<ScheduleItem> = mutableListOf()
        val response = ScheduleResponse(data)
        val key = "eventsnextleague.php"

        runBlocking {
            Mockito.`when`(apiRespository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", ScheduleResponse::class.java))
                .thenReturn(response)

            presenter.getScheduleList(scheduleItem = key, leagueID= "647900")
            view.hideLoading()
            Mockito.verify(view).hideLoading()
            view.showScheduleList(data)
            Mockito.verify(view).showScheduleList(data)
        }
    }


    @Test
    fun getScheduleSearch(){
        val data: MutableList<ScheduleItem> = mutableListOf()
        val response = ScheduleSearchResponse(data)
        val key = "Juventus"

        runBlocking {
            Mockito.`when`(apiRespository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", ScheduleSearchResponse::class.java))
                .thenReturn(response)

            presenter.getScheduleSearch(keyword = key)

            view.showScheduleList(data)
            Mockito.verify(view).showScheduleList(data)
        }
    }

}