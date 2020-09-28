package com.aripratom.footballleaguesubmission2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.activities.PlayerDetailActivity
import com.aripratom.footballleaguesubmission2.ui.model.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_player.view.*
import org.jetbrains.anko.startActivity

class PlayerAdapter (private val player: MutableList<Player>)
    : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false))

    override fun getItemCount(): Int = player.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(player[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(player: Player) {

            itemView.tvPlayerName.text = player.playerName
            itemView.tvPlayerPosition.text = player.playerPosition
            player.playerPhoto?.let { Picasso.get().load(it).into(itemView.imgPlayer) }

            itemView.setOnClickListener {
                itemView.context.startActivity<PlayerDetailActivity>("player" to player)
            }
        }
    }
}