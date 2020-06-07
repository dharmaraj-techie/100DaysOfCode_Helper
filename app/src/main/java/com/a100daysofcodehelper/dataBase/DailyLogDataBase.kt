package com.a100daysofcodehelper.dataBase

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [DailyLog::class], version = 1, exportSchema = false)
abstract class DailyLogDataBase: RoomDatabase() {

    abstract val dailyLogDao: DailyLogDao
    companion object {

        @Volatile
        private var INSTANCE: DailyLogDataBase? = null

        fun getInstance(context: Context): DailyLogDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DailyLogDataBase::class.java,
                        "daily_log_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}