package com.aripratom.footballleaguesubmission2.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.aripratom.footballleaguesubmission2.ui.model.ScheduleItem
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
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(ScheduleItem.TABLE_FAVORITE, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)