package com.a100daysofcodehelper.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DailyLogDao{

    @Insert
    fun insert(dailyLog: DailyLog)

    @Update
    fun update(dailyLog: DailyLog)

    @Query("SELECT * FROM daily_log_table WHERE id = :id")
    fun get(id: Long):DailyLog?

    @Query("SELECT * FROM daily_log_table ORDER BY id DESC")
    fun getAllLogs(): LiveData<List<DailyLog>>

    @Query("SELECT * FROM daily_log_table ORDER BY id DESC LIMIT 1")
    fun getLastLog(): DailyLog?
}