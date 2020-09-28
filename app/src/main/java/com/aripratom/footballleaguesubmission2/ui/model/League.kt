package com.aripratom.footballleaguesubmission2.ui.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League (
    @SerializedName("idLeague")
    var leagueID: String? = "",

    @SerializedName("strLeague")
    var leagueName: String? = "",

    @SerializedName("strLogo")
    var leagueLogo: String? = "",

    @SerializedName("strBadge")
    var leagueBadge: String? = "",

    @SerializedName("strDescriptionEN")
    var leagueDesc: String? = ""

) : Parcelable {
    override fun toString(): String {
        return leagueName.orEmpty()
    }
}
