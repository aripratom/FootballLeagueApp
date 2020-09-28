package com.aripratom.footballleaguesubmission2.ui.model

import com.google.gson.annotations.SerializedName

data class LeagueResponse(
    @SerializedName("countrys")
    val leagues: MutableList<League>)
