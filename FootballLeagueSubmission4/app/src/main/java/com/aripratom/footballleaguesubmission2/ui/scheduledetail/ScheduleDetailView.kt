package com.aripratom.footballleaguesubmission2.ui.scheduledetail

import com.aripratom.footballleaguesubmission2.ui.model.TeamItem

interface ScheduleDetailView {
    fun showHomeLogo(schedule : List<TeamItem>)
    fun showAwayLogo(schedule : List<TeamItem>)
}