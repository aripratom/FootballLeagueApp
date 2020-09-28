package com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.team.player


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.ui.adapter.PlayerAdapter
import com.aripratom.footballleaguesubmission2.ui.model.Player
import com.aripratom.footballleaguesubmission2.ui.model.TeamItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_player.*

/**
 * A simple [Fragment] subclass.
 */
class PlayerFragment : Fragment(), PlayerView {
    private var player: MutableList<Player> = mutableListOf()

    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter
    private var teamName : TeamItem? = null
    private var playerID: Player? = null

    override fun showPlayerList(data: List<Player>) {
        player.clear()
        player.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_player.layoutManager = GridLayoutManager(activity,2)
        adapter = PlayerAdapter(player)
        rv_player.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()

        val bundle = arguments
        teamName = bundle?.getParcelable("team")

        presenter = PlayerPresenter(this, request, gson)
        presenter.getPlayerData(teamName?.teamName)

    }


}
