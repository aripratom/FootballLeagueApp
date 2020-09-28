package com.aripratom.footballleaguesubmission2.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.aripratom.footballleaguesubmission2.R
import com.aripratom.footballleaguesubmission2.ui.model.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {

    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        player = intent.getParcelableExtra("player")

        supportActionBar?.title = player.playerName

        getDetail(player)
    }

    private fun getDetail(player: Player){
        Picasso.get().load(player.playerBackground).into(imgPlayerBG)
        tvWeight.text = player.playerWeight
        tvHeight.text = player.playerHeight
        tvPostition.text = player.playerPosition
        tvNation.text = player.playerNation
        tvDesc.text = player.playerDesc
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
