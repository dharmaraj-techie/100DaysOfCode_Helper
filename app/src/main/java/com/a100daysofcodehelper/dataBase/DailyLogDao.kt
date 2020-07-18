package com.a100daysofcodehelper.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DailyLogDao{

    @Insert
    fun insert(dailyLog: DailyLog)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg dailyLog: DailyLog)

    @Query("SELECT * FROM daily_log_table ORDER BY id DESC")
    fun getAllLogs(): List<DailyLog>

    @Query("SELECT * FROM daily_log_table ORDER BY id DESC LIMIT 1")
    fun getLastLog(): DailyLog?

}