package com.aripratom.footballleaguesubmission2.ui.league

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.adapter.LeagueAdapter
import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.ui.model.League
import com.aripratom.footballleaguesubmission2.ui.model.TeamItem
import com.aripratom.footballleaguesubmission2.util.EspressoIdlingResource
import com.aripratom.footballleaguesubmission2.util.invisible
import com.aripratom.footballleaguesubmission2.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_league.*


class LeagueFragment : Fragment(), LeagueView {


    private var leagues: MutableList<League> = mutableListOf()
    private var team: MutableList<TeamItem> = mutableListOf()
    private lateinit var adapter: LeagueAdapter
    private lateinit var presenter: LeaguePresenter


    override fun showLoading() {
        try {
            progressBar3.visible()
        }
        catch(ex:Exception){
            ex.printStackTrace()
        }
    }

    override fun hideLoading() {
        try {
            progressBar3.invisible()
        }
        catch(ex:Exception){
            ex.printStackTrace()
        }
    }

    override fun showLeagueList(data: List<League>) {
            leagues.clear()
            leagues.addAll(data)
            adapter.notifyDataSetChanged()
    }

    override fun showTeamList(data: List<TeamItem>) {
            team.clear()
            team.addAll(data)
            adapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return inflater.inflate(R.layout.fragment_league, container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_legue.layoutManager = GridLayoutManager(activity,2)
        adapter = LeagueAdapter(leagues)
        rv_legue.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()

        presenter = LeaguePresenter(this,request, gson)
        presenter.getLeagueList(league = "")
    }
}