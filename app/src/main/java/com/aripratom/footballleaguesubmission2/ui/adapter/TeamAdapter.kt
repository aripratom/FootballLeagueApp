package com.aripratom.footballleaguesubmission2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.activities.TeamDetailActivity
import com.aripratom.footballleaguesubmission2.ui.model.TeamItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.startActivity

class TeamAdapter (private val team: MutableList<TeamItem>)
    : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false))

    override fun getItemCount(): Int = team.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(team[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(team: TeamItem) {

            itemView.tvClubName.text = team.teamName
            team.teamBadge?.let { Picasso.get().load(it).into(itemView.imgClub) }

            itemView.setOnClickListener {
                itemView.context.startActivity<TeamDetailActivity>("team" to team)
            }
        }
    }
}