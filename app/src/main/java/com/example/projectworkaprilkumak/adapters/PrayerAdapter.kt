package com.example.projectworkaprilkumak.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectworkaprilkumak.databinding.ItemPrayersBinding
import com.example.projectworkaprilkumak.datas.Prayers

class PrayerAdapter(var prayers: MutableList<Prayers>) : RecyclerView.Adapter<PrayerAdapter.PrayerHolder>(){
    class PrayerHolder(binding: ItemPrayersBinding):RecyclerView.ViewHolder(binding.root){
        var prayerA = binding.prayerAvatar
        var prayerN = binding.prayerName
        var prayerD = binding.prayerDate
        var prayerT = binding.prayerTxt
        var prayerSh = binding.prayerShares
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayerHolder {
        return PrayerHolder(ItemPrayersBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PrayerHolder, position: Int) {
        var prayer = prayers[position]
        holder.prayerA.setImageResource(prayer.prayerAvatar)
        holder.prayerN.text = prayer.prayerText
        holder.prayerD.text = prayer.date
        holder.prayerT.text = prayer.prayerText
        holder.prayerSh.text = prayer.prayerShares
    }

    override fun getItemCount(): Int {
        return prayers.size
    }
}