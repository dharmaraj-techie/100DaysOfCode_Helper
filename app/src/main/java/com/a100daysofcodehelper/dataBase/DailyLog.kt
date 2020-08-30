package com.a100daysofcodehelper.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "daily_log_table",indices = [Index(value = ["date"], unique = true)])
data class DailyLog(
                    @PrimaryKey(autoGenerate = true)
                    val id : Long = 0L,

                    @ColumnInfo(name = "daily_log")
                    var log:String = "",

                    @ColumnInfo(name = "date")
                    var date: String = "") {

    fun formattedDate(): String {
        return date.substring(0, 3) + "\n" + date.substring(5, date.lastIndex - 4)
    }
}