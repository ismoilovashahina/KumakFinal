package com.example.projectworkaprilkumak.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectworkaprilkumak.databinding.FragmentDonateBinding
import com.example.projectworkaprilkumak.databinding.ItemCategoryBinding
import com.example.projectworkaprilkumak.datas.DonateData

class DonateAdapter(val amounts:MutableList<DonateData>, var onClick:MyAmountInterface) : RecyclerView.Adapter<DonateAdapter.DonateViewHolder>(){
    var selectedPos = -1

    class DonateViewHolder(binding: ItemCategoryBinding):RecyclerView.ViewHolder(binding.root){

        var amountMoney = binding.categoryText
        var card = binding.categoryMain

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonateViewHolder {
        return DonateViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DonateViewHolder, position: Int) {

        var amount = amounts[position]
        holder.amountMoney.text = amount.amount

        if(selectedPos == position){
            if (amount.status){
                amount.status = false
                holder.card.setCardBackgroundColor(Color.parseColor("#1EB960"))
                holder.amountMoney.setTextColor(Color.WHITE)

            }
        }
        else{
            amount.status = true
            holder.card.setCardBackgroundColor(Color.WHITE)
            holder.amountMoney.setTextColor(Color.parseColor("#1EB960"))
        }

        holder.card.setOnClickListener {

            onClick.onClick(amount, position)
            notifyItemChanged(selectedPos);
            selectedPos = position
            notifyItemChanged(selectedPos);
        }
    }

    override fun getItemCount(): Int = amounts.size

    interface MyAmountInterface{
        fun onClick(amount:DonateData, position: Int)
    }

}