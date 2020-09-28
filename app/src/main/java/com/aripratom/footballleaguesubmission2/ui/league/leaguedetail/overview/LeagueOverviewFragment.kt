package com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.overview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.model.League
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_league_overview.*

/**
 * A simple [Fragment] subclass.
 */
class LeagueOverviewFragment : Fragment() {

    var league : League? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_league_overview, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bundle = arguments
        league = bundle?.getParcelable("league")

        LeagueNameDetail.text = league?.leagueName
        LeagueDesc.text = league?.leagueDesc
        Picasso.get().load(league?.leagueBadge).into(LeagueLogoDetail)


    }

}
