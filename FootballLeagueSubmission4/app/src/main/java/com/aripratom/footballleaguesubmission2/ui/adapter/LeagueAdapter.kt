package com.aripratom.footballleaguesubmission2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.activities.LeagueDetailActivity
import com.aripratom.footballleaguesubmission2.ui.model.League
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_league.view.*
import org.jetbrains.anko.startActivity

class LeagueAdapter(
    private val leagues: MutableList<League>
) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(leagues[position])
    }

    override fun getItemCount(): Int = leagues.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(league: League) {

            itemView.LeagueName.text = league.leagueName

            league.leagueBadge?.let { Picasso.get().load(it).into(itemView.LeagueLogo) }

            itemView.setOnClickListener {
                itemView.context.startActivity<LeagueDetailActivity>("league" to league)
            }
        }
    }
}