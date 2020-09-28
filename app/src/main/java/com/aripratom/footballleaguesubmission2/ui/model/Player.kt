package com.aripratom.footballleaguesubmission2.ui.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    @SerializedName("idPlayer")
    var playerID: String? = "",

    @SerializedName("strPlayer")
    var playerName: String? = "",

    @SerializedName("strPosition")
    var playerPosition: String? = "",

    @SerializedName("strDescriptionEN")
    var playerDesc: String? = "",

    @SerializedName("strHeight")
    var playerHeight: String? = "",

    @SerializedName("strWeight")
    var playerWeight: String? = "",

    @SerializedName("strNationality")
    var playerNation: String? = "",

    @SerializedName("strCutout")
    var playerPhoto: String? = "",

    @SerializedName("strFanart1")
    var playerBackground: String? = ""
) : Parcelable