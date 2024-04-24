package com.example.projectworkaprilkumak.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectworkaprilkumak.databinding.ItemCategoryBinding
import com.example.projectworkaprilkumak.datas.MainCategory

class MainCategoryAdapter(var mainCategories:Array<MainCategory>, var onClick: MyCategoryInterface) : RecyclerView.Adapter<MainCategoryAdapter.MainCategoryHolder>(){
    var selectedPos = -1

    class MainCategoryHolder(binding: ItemCategoryBinding):RecyclerView.ViewHolder(binding.root){
        var category_main = binding.categoryMain
        var category_text = binding.categoryText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCategoryHolder {
        return MainCategoryHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MainCategoryHolder, position: Int) {
        var category = mainCategories[position]
        holder.category_text.text = category.categoryName

        if(selectedPos == position){
            if (category.status){
                category.status = false
                holder.category_main.setCardBackgroundColor(Color.parseColor("#1EB960"))
                holder.category_text.setTextColor(Color.WHITE)
            }
        }
        else{
            category.status = true
            holder.category_main.setCardBackgroundColor(Color.WHITE)
            holder.category_text.setTextColor(Color.parseColor("#1EB960"))
        }

        holder.category_main.setOnClickListener {

            onClick.onItemClick(category, position)
            notifyItemChanged(selectedPos);
            selectedPos = position
            notifyItemChanged(selectedPos);
        }
    }

    override fun getItemCount(): Int {
        return mainCategories.size
    }

    interface MyCategoryInterface{
        fun onItemClick(category: MainCategory, position: Int)
    }
}