package com.aripratom.footballleaguesubmission2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.api.ApiRepository
import com.aripratom.footballleaguesubmission2.api.TheSportDBApi
import com.aripratom.footballleaguesubmission2.ui.model.Klasemen
import com.aripratom.footballleaguesubmission2.ui.model.TeamResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_klasemen.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class KlasemenAdapter (private val klasemen: MutableList<Klasemen>, private val  gson: Gson, private val apiRepository: ApiRepository)
    : RecyclerView.Adapter<KlasemenAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_klasemen, parent, false))

    override fun getItemCount(): Int = klasemen.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(klasemen[position], gson, apiRepository)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(klasemen: Klasemen, gson: Gson, apiRepository: ApiRepository) {

            val pos = adapterPosition + 1
            itemView.no_klasemen.text = pos.toString()
            itemView.tvClubNameKlasemen.text = klasemen.name
            itemView.tvWin.text = klasemen.win
            itemView.tvDraw.text = klasemen.draw
            itemView.tvLose.text = klasemen.loss
            itemView.tvTotal.text = klasemen.total


            GlobalScope.launch(Dispatchers.Main) {
                val data = gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getTeamLogo(klasemen.teamid)).await(),
                    TeamResponse::class.java
                )

                Picasso.get().load(data.team[0].teamBadge).into(itemView.imgClubKlasemen)

            }
        }

    }
}