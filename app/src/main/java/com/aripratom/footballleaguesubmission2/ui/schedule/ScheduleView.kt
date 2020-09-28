package com.aripratom.footballleaguesubmission2.ui.schedule

import com.aripratom.footballleaguesubmission2.ui.model.League
import com.aripratom.footballleaguesubmission2.ui.model.ScheduleItem

interface ScheduleView {
    fun showLoading()
    fun hideLoading()
    fun showScheduleList(data: List<ScheduleItem>)
    fun showLeagueList(data: List<League>)

}