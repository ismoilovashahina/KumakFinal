package com.example.projectworkaprilkumak.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.databinding.ItemMyfundraisingBinding
import com.example.projectworkaprilkumak.datas.FundsImage
import com.example.projectworkaprilkumak.datas.MyFundraisingData
import com.google.firebase.firestore.FirebaseFirestore

class MyFundraisingAdapter(var context: Context,var myFundraisingList:MutableList<MyFundraisingData>,var editClick: onEditClick) :
    RecyclerView.Adapter<MyFundraisingAdapter.MyFundraisingHolder>(){
      var sliderList = ArrayList<FundsImage>()
      lateinit var firebaseFireStore: FirebaseFirestore
    class MyFundraisingHolder(binding: ItemMyfundraisingBinding) : RecyclerView.ViewHolder(binding.root){
        var title = binding.urgentTitle
        var img = binding.urgentI
        var raised = binding.urgentRaisedFund
        var toRaise = binding.urgentToRaise
        var donN = binding.urgentDonatorQuantity
        var dayL = binding.UrgentLeftDays
        var edit = binding.edit
        var share = binding.share
        var progress = binding.itemFundProgress

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFundraisingHolder {
      return MyFundraisingHolder(ItemMyfundraisingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyFundraisingHolder, position: Int) {
        var myFundraising = myFundraisingList[position]
        firebaseFireStore = FirebaseFirestore.getInstance()
        MySecondAsyncTask(holder.progress,position,holder.img).execute()

        holder.title.text = myFundraising.title
        holder.toRaise.text = myFundraising.toRaise.toString()
        holder.raised.text = myFundraising.raised.toString()
        holder.donN.text = myFundraising.donN.toString()
        holder.dayL.text = myFundraising.daysLeft.toString()
        anim(holder.itemView)

        holder.edit.setOnClickListener {
            editClick.editClick(myFundraisingList[position])
        }
        holder.share.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT,"${myFundraising.title}\n\nRaised from ${myFundraising.raised} to ${myFundraising.toRaise}\n\nDonators: ${myFundraising.donN}\n\nDars left: ${myFundraising.daysLeft}\n\nCreated by: Shahina Ismoilova")
            context.startActivity(Intent.createChooser(intent,"Ulashish"))
        }


    }

    override fun getItemCount(): Int {
        return myFundraisingList.size
    }
    private fun getImageFromFirebase() {
        firebaseFireStore.collection("images")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val result = it.result
                    result?.forEach { queryDocumentSnapshot ->
                        val fundsImage = queryDocumentSnapshot.toObject(FundsImage::class.java)
                        sliderList.add(fundsImage)
                    }
                }
            }
    }
    fun anim(view: View){
        val anim = AnimationUtils.loadAnimation(context, R.anim.anim_fund)
        view.startAnimation(anim)
    }
    @SuppressLint("StaticFieldLeak")
    inner class MySecondAsyncTask(var progress:ProgressBar,var position: Int,var imageView: ImageView):AsyncTask<Void?,Void?,Void?>(){
        @Deprecated("Deprecated in Java",
            ReplaceWith("super.onPreExecute()", "android.os.AsyncTask")
        )
        override fun onPreExecute() {
            progress.visibility = View.VISIBLE
            progress.setProgress(100,true)
            super.onPreExecute()

        }
        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg p0: Void?): Void? {
            getImageFromFirebase()
            return null
        }

        @Deprecated("Deprecated in Java",
            ReplaceWith("super.onPostExecute(result)", "android.os.AsyncTask")
        )
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            progress.setProgress(0,false)
            progress.visibility = View.INVISIBLE
            refresh(progress,position,imageView)
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    private fun refresh(progress: ProgressBar,position: Int,imageView: ImageView){
       val handler = Handler()
        handler.postDelayed({
            if (sliderList.isEmpty()){
                progress.visibility = View.VISIBLE
                progress.setProgress(100,true)
                refresh(progress,position,imageView)
            }else{
                progress.setProgress(100,false)
                progress.visibility = View.INVISIBLE
                pickUpImages(position,imageView)
            }
        },500)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun pickUpImages(position: Int, imageView: ImageView) {
        sliderList.forEach {
            Glide.with(context.applicationContext)
                .load(myFundraisingList[position].imageLink)
                .fitCenter() // surat o`rtasini hisobga olgan holda
                .diskCacheStrategy(DiskCacheStrategy.ALL) // keshga saqlash
                .placeholder(R.drawable.photo)
                .into(imageView)

        }

    }
    fun delAll(){
        myFundraisingList.clear()
    }


    fun sortMyFundraisingByMax(){
        myFundraisingList.sortByDescending { it.toRaise }
        notifyDataSetChanged()
    }
    fun sortMyFundraisingByMin(){
        myFundraisingList.sortBy { it.toRaise }
        notifyDataSetChanged()
    }
    interface onEditClick{
        fun editClick(myFundraisingData: MyFundraisingData)
    }
}
