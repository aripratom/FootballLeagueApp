package com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.overview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.model.TeamItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_team_overview.*

/**
 * A simple [Fragment] subclass.
 */
class TeamOverviewFragment : Fragment() {
    var team : TeamItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bundle = arguments
        team = bundle?.getParcelable("team")

        TeamNameDetail.text = team?.teamName
        TeamDesc.text = team?.teamDesc
        Picasso.get().load(team?.teamBadge).into(TeamLogoDetail)


    }


}
