package com.aripratom.footballleaguesubmission2.ui.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScheduleItem(
    var id: Long?,

    @SerializedName("idEvent")
    var eventId: String? = "",

    @SerializedName("strDate")
    var matchDate: String? = "",

    @SerializedName("strHomeTeam")
    var homeTeam: String? = "",

    @SerializedName("strAwayTeam")
    var awayTeam: String? = "",

    @SerializedName("intHomeScore")
    var homeTeamScore: String? = "",

    @SerializedName("intAwayScore")
    var awayTeamScore: String? = "",

    @SerializedName("strHomeGoalDetails")
    var homeScorer: String? = "",

    @SerializedName("strAwayGoalDetails")
    var awayScorer: String? = "",

    @SerializedName("intHomeShots")
    var homeShot: String? = "",

    @SerializedName("intAwayShots")
    var awayShot: String? = "",

    @SerializedName("strHomeLineupGoalkeeper")
    var homeGK: String? = "",

    @SerializedName("strAwayLineupGoalkeeper")
    var awayGK: String? = "",

    @SerializedName("strHomeLineupDefense")
    var homeDF: String? = "",

    @SerializedName("strAwayLineupDefense")
    var awayDF: String? = "",

    @SerializedName("strHomeLineupMidfield")
    var homeMF: String? = "",

    @SerializedName("strAwayLineupMidfield")
    var awayMF: String? = "",

    @SerializedName("strHomeLineupForward")
    var homeFW: String? = "",

    @SerializedName("strAwayLineupForward")
    var awayFW: String? = "",

    @SerializedName("strHomeLineupSubstitutes")
    var homeSubs: String? = "",

    @SerializedName("strAwayLineupSubstitutes")
    var awaySubs: String? = "",

    @SerializedName("strTeamBadge")
    var teamBadge: String? = "",

    @SerializedName("idHomeTeam")
    var homeTeamID: String? = "",

    @SerializedName("idAwayTeam")
    var awayTeamID: String? = "",

    @SerializedName("strSport")
    var Soccer: String? = ""

) : Parcelable {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_SCORER: String = "HOME_SCORER"
        const val AWAY_SCORER: String = "AWAY_SCORER"
        const val HOME_SHOT: String = "HOME_SHOT"
        const val AWAY_SHOT: String = "AWAY_SHOT"
        const val HOME_GK: String = "HOME_GK"
        const val AWAY_GK: String = "AWAY_GK"
        const val HOME_DF: String = "HOME_DF"
        const val AWAY_DF: String = "AWAY_DF"
        const val HOME_MF: String = "HOME_MF"
        const val AWAY_MF: String = "AWAY_MF"
        const val HOME_FW: String = "HOME_FW"
        const val AWAY_FW: String = "AWAY_FW"
        const val HOME_SUB: String = "HOME_SUB"
        const val AWAY_SUB: String = "AWAY_SUB"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val HOME_ID: String = "HOME_ID"
        const val AWAY_ID: String = "AWAY_ID"
        const val SOCCER: String = "SOCCER"
    }
}