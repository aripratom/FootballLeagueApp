package com.aripratom.footballleaguesubmission2.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.aripratom.footballleaguesubmission2.ui.model.ScheduleItem
import com.aripratom.footballleaguesubmission2.ui.model.TeamItem
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "FavoriteMatch.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(context.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(ScheduleItem.TABLE_FAVORITE, true,
            ScheduleItem.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ScheduleItem.EVENT_ID to TEXT + UNIQUE,
            ScheduleItem.MATCH_DATE to TEXT,
            ScheduleItem.HOME_TEAM to TEXT,
            ScheduleItem.AWAY_TEAM to TEXT,
            ScheduleItem.HOME_SCORE to TEXT,
            ScheduleItem.AWAY_SCORE to TEXT,
            ScheduleItem.HOME_SCORER to TEXT,
            ScheduleItem.AWAY_SCORER to TEXT,
            ScheduleItem.HOME_SHOT to TEXT,
            ScheduleItem.AWAY_SHOT to TEXT,
            ScheduleItem.HOME_GK to TEXT,
            ScheduleItem.AWAY_GK to TEXT,
            ScheduleItem.HOME_DF to TEXT,
            ScheduleItem.AWAY_DF to TEXT,
            ScheduleItem.HOME_MF to TEXT,
            ScheduleItem.AWAY_MF to TEXT,
            ScheduleItem.HOME_FW to TEXT,
            ScheduleItem.AWAY_FW to TEXT,
            ScheduleItem.HOME_SUB to TEXT,
            ScheduleItem.AWAY_SUB to TEXT,
            ScheduleItem.TEAM_BADGE to TEXT,
            ScheduleItem.HOME_ID to TEXT,
            ScheduleItem.AWAY_ID to TEXT,
            ScheduleItem.SOCCER to TEXT)

        db.createTable(TeamItem.TABLE_TEAM, true,
            TeamItem.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamItem.TEAM_ID to TEXT + UNIQUE,
            TeamItem.TEAM_NAME to TEXT,
            TeamItem.FORMED_YEAR to TEXT,
            TeamItem.STADIUM to TEXT,
            TeamItem.DESCIPTION to TEXT,
            TeamItem.BADGE to TEXT,
            TeamItem.SOCCER to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(ScheduleItem.TABLE_FAVORITE, true)
        db.dropTable(TeamItem.TABLE_TEAM, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)