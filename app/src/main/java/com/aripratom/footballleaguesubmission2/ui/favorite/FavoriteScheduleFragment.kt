package com.aripratom.footballleaguesubmission2.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.database.database
import com.aripratom.footballleaguesubmission2.ui.adapter.ScheduleAdapter
import com.aripratom.footballleaguesubmission2.ui.model.ScheduleItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_favorite_schedule.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 */
class FavoriteScheduleFragment : Fragment() {

    private var schedule: MutableList<ScheduleItem> = mutableListOf()
    private lateinit var adapter: ScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_schedule, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_favorite.layoutManager = LinearLayoutManager(activity)
        adapter = ScheduleAdapter(schedule, apiRepository = ApiRepository(), gson = Gson())
        rv_favorite.adapter = adapter

        swipeRefresh.onRefresh {
            showFavorite()
        }
    }
    override fun onResume() {
        super.onResume()
        showFavorite()
    }
    private fun showFavorite() {
        schedule.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(ScheduleItem.TABLE_FAVORITE)
            val fav = result.parseList(classParser<ScheduleItem>())
            schedule.addAll(fav)
            adapter.notifyDataSetChanged()
        }
    }

}
