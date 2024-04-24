package com.example.projectworkaprilkumak.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectworkaprilkumak.databinding.ItemInterestBinding
import com.example.projectworkaprilkumak.datas.SelectInterest

class SelectInterestAdapter(var interests:MutableList<SelectInterest>, var selectInterest: InterestInterface):RecyclerView.Adapter<SelectInterestAdapter.InterestHolder>() {

    class InterestHolder(binding: ItemInterestBinding):RecyclerView.ViewHolder(binding.root){
        var in_ic = binding.interestIc
        var in_t = binding.interestT
        var interestMain = binding.cardViewInterest
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestHolder {
        return InterestHolder(ItemInterestBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return interests.size
    }
    override fun onBindViewHolder(holder: InterestHolder, position: Int) {
        var interest = interests[position]
        holder.in_ic.setImageResource(interest.interestIcon)
        holder.in_t.text = interest.interestText

//        if(selectedPos == position){
//            holder.interestMain.setCardBackgroundColor(Color.YELLOW)
//        }

        holder.interestMain.setOnClickListener {
//            selectInterest.onInterestClick(interest, position)
//            notifyItemChanged(selectedPos)
//            selectedPos = position
//            notifyItemChanged(selectedPos)
            if (interest.status){
                interest.status=false
                holder.interestMain.setCardBackgroundColor(Color.parseColor("#FFFFFF"))

            }else{
                interest.status=true
                holder.interestMain.setCardBackgroundColor(Color.YELLOW)

            }
        }

    }

    interface InterestInterface{
        fun onInterestClick(interest: SelectInterest, position: Int)
    }
}

