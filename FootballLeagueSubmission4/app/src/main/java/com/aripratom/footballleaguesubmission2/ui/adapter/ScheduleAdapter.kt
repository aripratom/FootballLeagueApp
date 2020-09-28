package com.aripratom.footballleaguesubmission2.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.activities.ScheduleDetailActivity
import com.aripratom.footballleaguesubmission2.ui.api.ApiRespository
import com.aripratom.footballleaguesubmission2.ui.api.TheSportDBApi
import com.aripratom.footballleaguesubmission2.ui.model.ScheduleItem
import com.aripratom.footballleaguesubmission2.ui.model.TeamResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_schedule.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import java.text.SimpleDateFormat
import java.util.*

class ScheduleAdapter(
    private val schedule: MutableList<ScheduleItem>,
    private val gson: Gson,
    private val apiRespository: ApiRespository
) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_schedule,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = schedule.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(schedule[position], gson, apiRespository)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bindItem(schedule: ScheduleItem, gson: Gson, apiRespository: ApiRespository) {
            val format = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
            val sdf = SimpleDateFormat("E, dd MMMM yyyy", Locale.getDefault())
            if (schedule.matchDate != null) {
                val date: String = sdf.format(format.parse(schedule.matchDate))
                itemView.tvDate.text = date
            }

            GlobalScope.launch {
                val data = gson.fromJson(
                    apiRespository.doRequest(TheSportDBApi.getTeamLogo(schedule.homeTeamID)).await(),
                    TeamResponse::class.java
                )

                    Picasso.get().load(data.team[0].teamBadge).into(itemView.imgHomeTeam)

            }

            GlobalScope.launch {
                val data = gson.fromJson(
                    apiRespository.doRequest(TheSportDBApi.getTeamLogo(schedule.awayTeamID)).await(),
                    TeamResponse::class.java
                )
                 Picasso.get().load(data.team[0].teamBadge).into(itemView.imgAwayTeam)
            }


            itemView.tvHomeTeam.text = schedule.homeTeam
            itemView.tvAwayTeam.text = schedule.awayTeam
            if (schedule.homeTeamScore == null) {
                itemView.tvScore.text = "VS"
            } else {
                itemView.tvScore.text = "${schedule.homeTeamScore} VS ${schedule.awayTeamScore}"
            }
            itemView.setOnClickListener {
                itemView.context.startActivity<ScheduleDetailActivity>("schedule" to schedule)
            }


        }

    }

}
