package com.aripratom.footballleaguesubmission2.ui.league

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.adapter.LeagueAdapter
import com.aripratom.footballleaguesubmission2.ui.api.ApiRespository
import com.aripratom.footballleaguesubmission2.ui.model.League
import com.aripratom.footballleaguesubmission2.util.EspressoIdlingResource
import com.aripratom.footballleaguesubmission2.util.invisible
import com.aripratom.footballleaguesubmission2.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_league.*


class LeagueFragment : Fragment(), LeagueView {

    private var leagues: MutableList<League> = mutableListOf()
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
        if (!EspressoIdlingResource.idlingresource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
            leagues.clear()
            leagues.addAll(data)
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

        val request = ApiRespository()
        val gson = Gson()

        presenter = LeaguePresenter(this,request, gson)

        presenter.getLeagueList(league = null)
    }
}