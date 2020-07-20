package com.a100daysofcodehelper.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DailyLogDao{

    @Insert
    fun insert(dailyLog: DailyLog)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(vararg dailyLog: DailyLog)

    @Query("SELECT * FROM daily_log_table ORDER BY date DESC")
    fun getAllLogs(): List<DailyLog>

    @Query("SELECT * FROM daily_log_table ORDER BY date DESC LIMIT 1")
    fun getLastLog(): DailyLog?

    @Query("SELECT date FROM daily_log_table")
    fun getAllLogDates(): LiveData<List<String>>

}