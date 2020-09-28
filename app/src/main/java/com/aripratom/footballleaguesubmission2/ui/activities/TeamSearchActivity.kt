package com.aripratom.footballleaguesubmission2.ui.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.ui.adapter.TeamAdapter
import com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.team.TeamPresenter
import com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.team.TeamView
import com.aripratom.footballleaguesubmission2.ui.model.TeamItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_team_search.*

class TeamSearchActivity : AppCompatActivity(), TeamView  {


    private var team : MutableList<TeamItem> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_search)

        setSupportActionBar(toolbar_team_search)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        rv_team_search.layoutManager = LinearLayoutManager(this)
        adapter = TeamAdapter(team)
        rv_team_search.adapter = adapter

        val api = ApiRepository()
        val gson = Gson()

        presenter = TeamPresenter(this, api, gson)

        team_search.requestFocus()
        team_search.isIconified = false
        teamSearch(team_search)
    }

    private fun teamSearch(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                presenter.getTeamSearch(query.toString())
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamList(data: List<TeamItem>) {
        team.clear()
        team.addAll(data)
        adapter.notifyDataSetChanged()
    }


}
