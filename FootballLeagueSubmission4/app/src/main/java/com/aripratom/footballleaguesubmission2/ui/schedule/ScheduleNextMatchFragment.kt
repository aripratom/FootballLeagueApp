package com.aripratom.footballleaguesubmission2.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.adapter.ScheduleAdapter
import com.aripratom.footballleaguesubmission2.ui.api.ApiRespository
import com.aripratom.footballleaguesubmission2.ui.model.League
import com.aripratom.footballleaguesubmission2.ui.model.ScheduleItem
import com.aripratom.footballleaguesubmission2.util.EspressoIdlingResource
import com.aripratom.footballleaguesubmission2.util.invisible
import com.aripratom.footballleaguesubmission2.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_schedule_next_match.*

/**
 * A simple [Fragment] subclass.
 */
class ScheduleNextMatchFragment : Fragment(), ScheduleView {

    private var schedule: MutableList<ScheduleItem> = mutableListOf()
    private lateinit var presenter: SchedulePresenter
    private lateinit var adapter: ScheduleAdapter
    private var league : MutableList<League> = mutableListOf()
    private val scheduleItem : String = "eventsnextleague.php"
    private lateinit var spn_league_next : Spinner

    override fun showLoading() {
        try {
            progressBar_next.visible()
        }
        catch(ex:Exception){
            ex.printStackTrace()
        }
    }

    override fun hideLoading() {
        try {
            progressBar_next.invisible()
        }
        catch(ex:Exception){
            ex.printStackTrace()
        }
    }

    override fun showLeagueList(data: List<League>) {
        league.clear()
        league.addAll(data)
        spn_league_next.adapter = ArrayAdapter(context!!,android.R.layout.simple_spinner_dropdown_item, league)
    }

    override fun showScheduleList(data: List<ScheduleItem>) {
        schedule.clear()
        schedule.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_schedule_next_match, container, false)

        spn_league_next = view.findViewById(R.id.spn_league_next)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_schedule_next.layoutManager = LinearLayoutManager(activity)
        adapter = ScheduleAdapter(schedule, gson = Gson(), apiRespository = ApiRespository())
        rv_schedule_next.adapter = adapter

        val request = ApiRespository()
        val gson = Gson()

        presenter = SchedulePresenter(this,request, gson)
        EspressoIdlingResource.increment()
        presenter.getLeagueList(league = null)


        spn_league_next.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getScheduleList(scheduleItem, league[position].leagueID)

            }
        }
    }



}
