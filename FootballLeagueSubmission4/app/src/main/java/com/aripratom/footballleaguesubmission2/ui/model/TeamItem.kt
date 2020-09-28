package com.aripratom.footballleaguesubmission2.ui.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class TeamItem(
    var id: Long?,

    @SerializedName("idTeam")
    var teamID: String? = "",

    @SerializedName("strTeam")
    var teamName: String? = "",

    @SerializedName("intFormedYear")
    var formedYear: String? = "",

    @SerializedName("strStadium")
    var teamStadium: String? = "",

    @SerializedName("strDescriptionEN")
    var teamDesc: String? = "",

    @SerializedName("strTeamBadge")
    var teamBadge: String? = ""

) : Parcelable