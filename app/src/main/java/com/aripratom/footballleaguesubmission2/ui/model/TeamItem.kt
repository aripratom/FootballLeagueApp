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
    var teamBadge: String? = "",

    @SerializedName("strSport")
    var Soccer: String? = ""


) : Parcelable {
    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val FORMED_YEAR: String = "FORMED_YEAR"
        const val STADIUM: String = "STADIUM"
        const val DESCIPTION: String = "DESCIPTION"
        const val BADGE: String = "BADGE"
        const val SOCCER: String = "SOCCER"
    }
}