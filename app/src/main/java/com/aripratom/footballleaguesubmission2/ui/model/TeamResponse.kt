package com.aripratom.footballleaguesubmission2.ui.model

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("teams") val team: List<TeamItem>)