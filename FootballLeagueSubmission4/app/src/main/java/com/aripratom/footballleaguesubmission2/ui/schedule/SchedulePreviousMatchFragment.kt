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
import kotlinx.android.synthetic.main.fragment_schedule_previous_match.*

/**
 * A simple [Fragment] subclass.
 */
class SchedulePreviousMatchFragment : Fragment(), ScheduleView {

    private var schedule: MutableList<ScheduleItem> = mutableListOf()
    private lateinit var presenter: SchedulePresenter
    private lateinit var adapter: ScheduleAdapter
    private var league : MutableList<League> = mutableListOf()
    private val scheduleItem : String = "eventspastleague.php"
    private lateinit var spn_league_prev : Spinner


    override fun showLeagueList(data: List<League>) {
        league.clear()
        league.addAll(data)
        try {
            spn_league_prev.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, league)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    override fun showLoading() {
        try {
            progressBar_prev.visible()
        }
        catch(ex:Exception){
            ex.printStackTrace()
        }

    }

    override fun hideLoading() {
        try {
            progressBar_prev.invisible()
        }
        catch(ex:Exception){
            ex.printStackTrace()
        }

    }

    override fun showScheduleList(data: List<ScheduleItem>) {
       if (!EspressoIdlingResource.idlingresource.isIdleNow){
           EspressoIdlingResource.decrement()
       }
        schedule.clear()
        schedule.addAll(data)
        adapter.notifyDataSetChanged()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_schedule_previous_match, container, false)

        spn_league_prev = view.findViewById(R.id.spn_league_prev)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_schedule_previous.layoutManager = LinearLayoutManager(activity)
        adapter = ScheduleAdapter(schedule, gson = Gson(), apiRespository = ApiRespository())
        rv_schedule_previous.adapter = adapter

        val request = ApiRespository()
        val gson = Gson()

        presenter = SchedulePresenter(this,request, gson)
        EspressoIdlingResource.increment()
        presenter.getLeagueList(league = null)


        spn_league_prev.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getScheduleList(scheduleItem, league[position].leagueID)

            }
        }
    }


}
