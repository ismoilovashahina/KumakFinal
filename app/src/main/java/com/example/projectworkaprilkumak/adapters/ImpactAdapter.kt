package com.example.projectworkaprilkumak.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectworkaprilkumak.databinding.ItemImpactBinding
import com.example.projectworkaprilkumak.datas.ImpactData

class ImpactAdapter (var impactViews:MutableList<ImpactData>, var impactInterface: ImpactInterface):RecyclerView.Adapter<ImpactAdapter.ImpactHolder>(){
    class ImpactHolder(binding:ItemImpactBinding):RecyclerView.ViewHolder(binding.root){
        var impactIV = binding.impactI
        var impactTV = binding.impactT
        var impactCard = binding.impactC
    }
    interface ImpactInterface{
        fun onPress(impactData:ImpactData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImpactHolder =
         ImpactHolder(ItemImpactBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(holder: ImpactHolder, position: Int) {
        var impact = impactViews[position]
        holder.impactIV.setImageResource(impact.impactI)
        holder.impactTV.text = impact.impactT

        holder.impactCard.setOnClickListener { impactInterface.onPress(impact) }

    }

    override fun getItemCount(): Int = impactViews.size
}