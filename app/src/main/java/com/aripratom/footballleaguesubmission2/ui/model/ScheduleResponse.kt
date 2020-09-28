package com.aripratom.footballleaguesubmission2.ui.model

import com.google.gson.annotations.SerializedName

data class ScheduleResponse(
    @SerializedName("events")
    val schedule: List<ScheduleItem>)

data class ScheduleSearchResponse (@SerializedName("event")
    val schedule: List<ScheduleItem>
)