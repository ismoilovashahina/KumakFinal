package com.example.projectworkaprilkumak.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.databinding.ItemBookmarkBinding
import com.example.projectworkaprilkumak.databinding.ItemUrgentBinding
import com.example.projectworkaprilkumak.databinding.ItemUrgentFBinding
import com.example.projectworkaprilkumak.datas.UrgentFdata
import com.example.projectworkaprilkumak.sharedPreferences.MySharedPreferences

class UrgentFAdapter(var urgents:MutableList<UrgentFdata>, var myDonateInterface:DonateInterface, var myBookMarkInterface: BookMarkInterface):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private lateinit var bindingUrgentMain: ItemUrgentFBinding
//    private lateinit var bindingUrgentAll: ItemUrgentBinding
lateinit var context: Context


    companion object{
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private inner class UrgentFHolder(binding: ItemUrgentFBinding):RecyclerView.ViewHolder(binding.root){
        var urgent_image = binding.urgentI
        var urgent_title = binding.urgentTitle
        var urgent_raised = binding.urgentRaisedFund
        var urgent_toraise = binding.urgentToRaise
        var urgent_donators = binding.urgentDonatorQuantity
        var urgent_leftdays = binding.UrgentLeftDays
        var b_ic = binding.bIc
        var home_card = binding.homeCard
    }
    private inner class UrgentAllHolder(binding: ItemUrgentBinding):RecyclerView.ViewHolder(binding.root){
        var urgent_image = binding.urgentI
        var urgent_title = binding.urgentTitle
        var urgent_raised = binding.urgentRaisedFund
        var urgent_toraise = binding.urgentToRaise
        var urgent_donators = binding.urgentDonatorQuantity
        var urgent_leftdays = binding.UrgentLeftDays
        var b_ic = binding.bIc
        var main_card = binding.mainCard
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return UrgentFHolder(
                ItemUrgentFBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
        else {
            return UrgentAllHolder(
                ItemUrgentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return urgents.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (urgents[position].viewType === VIEW_TYPE_ONE) {
            var recyclerViewModel = urgents[position]
            (holder as UrgentFHolder).urgent_image.setImageResource(recyclerViewModel.u_i)
            holder.urgent_title.text = recyclerViewModel.u_t
            holder.urgent_raised.text = recyclerViewModel.u_raised.toString()
            holder.urgent_toraise.text = recyclerViewModel.u_toRaise.toString()
            holder.urgent_donators.text = recyclerViewModel.u_don.toString()
            holder.urgent_leftdays.text = recyclerViewModel.u_dLeft.toString()

            holder.b_ic.setOnClickListener {
                myBookMarkInterface.b_click(recyclerViewModel)
            }

            holder.home_card.setOnClickListener { myDonateInterface.onPress(recyclerViewModel) }
        } else if (urgents[position].viewType === VIEW_TYPE_TWO) {
            var recyclerViewModel2 = urgents[position]
            (holder as UrgentAllHolder).urgent_image.setImageResource(recyclerViewModel2.u_i)
            holder.urgent_title.text = recyclerViewModel2.u_t
            holder.urgent_raised.text = recyclerViewModel2.u_raised.toString()
            holder.urgent_toraise.text = recyclerViewModel2.u_toRaise.toString()
            holder.urgent_donators.text = recyclerViewModel2.u_don.toString()
            holder.urgent_leftdays.text = recyclerViewModel2.u_dLeft.toString()

            holder.b_ic.setOnClickListener {
                myBookMarkInterface.b_click(recyclerViewModel2)
            }
            holder.main_card.setOnClickListener { myDonateInterface.onPress(recyclerViewModel2) }
        }}



    override fun getItemViewType(position: Int): Int {
        return urgents[position].viewType
    }

    interface DonateInterface{
        fun onPress(donationData:UrgentFdata)
    }
    interface BookMarkInterface{
        fun b_click(donationData: UrgentFdata){
            donationData.bookmarked=true
        }
    }
}