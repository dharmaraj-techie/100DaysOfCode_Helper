package com.a100daysofcodehelper.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "daily_log_table")
data class DailyLog(
                    @PrimaryKey(autoGenerate = true)
                    val id : Long,

                    @ColumnInfo(name = "daily_log")
                    val log:String = "",

                    @ColumnInfo(name = "date")
                    val date: String = "") {

}