package com.aripratom.footballleaguesubmission2.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Klasemen (
    var teamid: String,
    var name: String,
    var total: String,
    var win: String,
    var draw: String,
    var loss: String
): Parcelable