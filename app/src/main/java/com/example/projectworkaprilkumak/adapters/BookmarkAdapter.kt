package com.example.projectworkaprilkumak.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectworkaprilkumak.databinding.ItemBookmarkBinding
import com.example.projectworkaprilkumak.datas.UrgentFdata

class BookmarkAdapter(var bookmarks:MutableList<UrgentFdata>, var bookmarkInterface: BooDonDetInterface):RecyclerView.Adapter<BookmarkAdapter.BookmarkHolder>(){
        class BookmarkHolder(binding: ItemBookmarkBinding) : RecyclerView.ViewHolder(binding.root){
            var bookmarkI = binding.urgentI
            var bookmarkT = binding.urgentTitle
            var bookmarkRaisedAmount = binding.urgentRaisedFund
            var bookmarkToRaise = binding.urgentToRaise
            var bookmarkD = binding.urgentDonatorQuantity
            var bookmarkLeftDays = binding.UrgentLeftDays

            var bookmarkCard = binding.bookmarkCard
        }

    interface BooDonDetInterface {
        fun onPress(booDonDet:UrgentFdata)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkHolder {
        return BookmarkHolder(ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: BookmarkHolder, position: Int) {
        var bookmark = bookmarks[position]
        holder.bookmarkI.setImageResource(bookmark.u_i)
        holder.bookmarkT.text = bookmark.u_t
        holder.bookmarkRaisedAmount.text = bookmark.u_raised.toString()
        holder.bookmarkToRaise.text = bookmark.u_toRaise.toString()
        holder.bookmarkD.text = bookmark.u_don.toString()
        holder.bookmarkLeftDays.text = bookmark.u_dLeft.toString()

        holder.bookmarkCard.setOnClickListener { bookmarkInterface.onPress(bookmark) }


    }

    override fun getItemCount(): Int {
        return bookmarks.size
    }
}

