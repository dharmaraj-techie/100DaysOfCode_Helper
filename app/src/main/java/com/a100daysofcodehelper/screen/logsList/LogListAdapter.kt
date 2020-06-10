package com.a100daysofcodehelper.screen.logsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a100daysofcodehelper.dataBase.DailyLog
import com.a100daysofcodehelper.databinding.DailyLogListItemBinding
import org.jetbrains.annotations.NotNull

class LogListAdapter (): RecyclerView.Adapter<DailyLogViewHolder>() {
    var data = listOf<DailyLog>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyLogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DailyLogListItemBinding.inflate(layoutInflater, parent, false)
        return DailyLogViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DailyLogViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }
}

class DailyLogViewHolder(private val binding: DailyLogListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(dailyLog: DailyLog){
        binding.dailyLogTV.text = dailyLog.log
        binding.dateTV.text = dailyLog.date
    }
}
