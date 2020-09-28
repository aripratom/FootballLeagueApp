package com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.team


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.ui.activities.TeamSearchActivity
import com.aripratom.footballleaguesubmission2.ui.adapter.TeamAdapter
import com.aripratom.footballleaguesubmission2.ui.model.League
import com.aripratom.footballleaguesubmission2.ui.model.TeamItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment(), TeamView {
    private var team: MutableList<TeamItem> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter
    private var leagues: League? = null

    override fun showLoading() {
        swipeRefreshTeam.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshTeam.isRefreshing = false
    }

    override fun showTeamList(data: List<TeamItem>) {
        swipeRefreshTeam.isRefreshing = false
        team.clear()
        team.addAll(data)
        adapter.notifyDataSetChanged()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        rv_team.layoutManager = GridLayoutManager(activity, 1)
        adapter = TeamAdapter(team)
        rv_team.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()

        val bundle = arguments
        leagues = bundle?.getParcelable("league")

        presenter = TeamPresenter(this, request, gson)
        //presenter.getTeamList(leagues?.leagueID)
        presenter.getTeamList(leagueName = leagues?.leagueName?.replace(" ", "%20"))

        Log.d("CCCCC", "${leagues?.leagueName}")
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)
        menuInflater.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.teamSearch -> {
                startActivity<TeamSearchActivity>()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}