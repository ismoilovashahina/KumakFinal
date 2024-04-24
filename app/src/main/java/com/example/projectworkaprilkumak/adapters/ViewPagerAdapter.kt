package com.example.projectworkaprilkumak.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectworkaprilkumak.databinding.ItemViewpagerBinding
import com.example.projectworkaprilkumak.datas.ViewPagerData

class ViewPagerAdapter (val vList: MutableList<ViewPagerData>) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>(){
    class ViewPagerHolder(binding:ItemViewpagerBinding):RecyclerView.ViewHolder(binding.root) {
        var vImg = binding.pic1
        var vTitle = binding.vTitle
        var vText = binding.vText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        return ViewPagerHolder(ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        var v = vList[position]

        holder.vText.text = v.vtext
        holder.vTitle.text = v.vtitle
        holder.vImg.setImageResource(v.vimg)

    }

    override fun getItemCount(): Int {
        return vList.size
    }

}