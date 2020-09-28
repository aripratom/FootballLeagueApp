package com.aripratom.footballleaguesubmission2.ui.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.adapter.ScheduleAdapter
import com.aripratom.footballleaguesubmission2.ui.api.ApiRespository
import com.aripratom.footballleaguesubmission2.ui.model.League
import com.aripratom.footballleaguesubmission2.ui.model.ScheduleItem
import com.aripratom.footballleaguesubmission2.ui.schedule.SchedulePresenter
import com.aripratom.footballleaguesubmission2.ui.schedule.ScheduleView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_schedule_search.*

class ScheduleSearchActivity : AppCompatActivity(), ScheduleView {

    private var schedule : MutableList<ScheduleItem> = mutableListOf()
    private lateinit var presenter: SchedulePresenter
    private lateinit var adapter: ScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_search)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        rv_schedule_search.layoutManager = LinearLayoutManager(this)
        adapter = ScheduleAdapter(schedule, apiRespository = ApiRespository(), gson = Gson())
        rv_schedule_search.adapter = adapter

        val api = ApiRespository()
        val gson = Gson()

        presenter = SchedulePresenter(this, api, gson)

        search.requestFocus()
        search.isIconified = false
        scheduleSearch(search)
    }

    private fun scheduleSearch(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                presenter.getScheduleSearch(query.toString())
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

    override fun showScheduleList(data: List<ScheduleItem>) {
        schedule.clear()
        schedule.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLeagueList(data: List<League>) {
    }

}
