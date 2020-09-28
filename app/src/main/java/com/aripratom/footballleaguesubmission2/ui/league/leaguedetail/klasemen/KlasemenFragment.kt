package com.aripratom.footballleaguesubmission2.ui.league.leaguedetail.klasemen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.ui.adapter.KlasemenAdapter
import com.aripratom.footballleaguesubmission2.ui.model.Klasemen
import com.aripratom.footballleaguesubmission2.ui.model.League
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_klasemen.*

/**
 * A simple [Fragment] subclass.
 */
class KlasemenFragment : Fragment(), KlasemenView {

    override fun showLoading() {
        try {
            swipeRefreshKlasemen.isRefreshing = true
        }
        catch(ex:Exception){
            ex.printStackTrace()
        }
    }

    override fun showKlasemen(data: List<Klasemen>) {
        klasemenList.clear()
        klasemenList.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun hideLoading() {
        try {
            swipeRefreshKlasemen.isRefreshing = false
        }
        catch(ex:Exception){
            ex.printStackTrace()
        }

    }

    private lateinit var presenter: KlasemenPresenter
    private lateinit var adapter: KlasemenAdapter
    private val apiRepository: ApiRepository = ApiRepository()
    private val gson: Gson = Gson()
    private val klasemenList = mutableListOf<Klasemen>()
    private var leagues: League? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_klasemen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = KlasemenAdapter(klasemenList, gson, apiRepository)
        presenter = KlasemenPresenter(this, apiRepository, gson)

        val bundle = arguments
        leagues = bundle?.getParcelable("league")
        presenter.getKlasemen(leagues?.leagueID!!)

        rvKlasemen.layoutManager = LinearLayoutManager(context)
        rvKlasemen.adapter = adapter
    }


}
