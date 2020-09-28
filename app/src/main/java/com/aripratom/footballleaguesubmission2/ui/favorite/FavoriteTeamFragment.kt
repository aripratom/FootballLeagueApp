package com.aripratom.footballleaguesubmission2.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.database.database
import com.aripratom.footballleaguesubmission2.ui.adapter.TeamAdapter
import com.aripratom.footballleaguesubmission2.ui.model.TeamItem
import kotlinx.android.synthetic.main.fragment_favorite_schedule.*
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : Fragment() {
    private var team: MutableList<TeamItem> = mutableListOf()
    private lateinit var adapter: TeamAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_team_fav.layoutManager = LinearLayoutManager(activity)
        adapter = TeamAdapter(team)
        rv_team_fav.adapter = adapter

        if (swipeRefresh != null) {
            swipeRefresh.onRefresh {
                showFavorite()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        team.clear()
        context?.database?.use {
            swipeRefreshFavTeam.isRefreshing = false
            val result = select(TeamItem.TABLE_TEAM)
            val favorite = result.parseList(classParser<TeamItem>())
            team.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}


