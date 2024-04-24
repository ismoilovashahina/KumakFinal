package com.example.projectworkaprilkumak.navbar

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.adapters.MyFundraisingAdapter
import com.example.projectworkaprilkumak.database.MyBase
import com.example.projectworkaprilkumak.databinding.FragmentMyFundraisingBinding
import com.example.projectworkaprilkumak.datas.MyFundraisingData
import com.example.projectworkaprilkumak.datas.MySortData
import com.example.projectworkaprilkumak.modules.MyEditingFundData
import com.example.projectworkaprilkumak.modules.PushingKeySaver
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore

const val TAG = "MyFunda"
class MyFundraisingFragment : Fragment() {
    private lateinit var binding: FragmentMyFundraisingBinding
    private lateinit var adapter: MyFundraisingAdapter
    lateinit var myBase: MyBase
    lateinit var firebaseDateBae:FirebaseDatabase
    lateinit var firebaseFireStore: FirebaseFirestore
    lateinit var reference: DatabaseReference
    var currentlySorting = ""
    private lateinit var list:MutableList<MyFundraisingData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMyFundraisingBinding.inflate(inflater, container, false)
        myBase = MyBase(requireContext())
        list = mutableListOf()

        insFireBase()

        MyAsyncTask().execute()


        binding.addFab.setOnClickListener { findNavController().navigate(R.id.createNewFundRaisingFragment) }
        binding.toolbar3.setNavigationOnClickListener {
           sorting()
        }

     return binding.root
    }

    // firebaseDatabase instlizatsiyasi
    private fun insFireBase(){
        firebaseDateBae = FirebaseDatabase.getInstance()
        firebaseFireStore = FirebaseFirestore.getInstance()
        reference = firebaseDateBae.getReference("funds")
    }

    // ma`lumotlarni sortlash
    private fun sorting(){
        var sortBy = myBase.getAllSort()[0].sortBy
        if (sortBy == "max"){
            sortMyFundraisingByMin()
            adapter.sortMyFundraisingByMin()
            myBase.editSort(MySortData(1,"min"))
            currentlySorting = "min"
        }else if (sortBy == "min"){
            sortMyFundraisingByMax()
            adapter.sortMyFundraisingByMax()
            adapter.notifyDataSetChanged()
            myBase.editSort(MySortData(1,"max"))
            currentlySorting = "max"
        }
    }

    // lifecycle bo`yicha OnResumega tushganida sortlashni tekshirib ketish
    private fun sortingOnResume(){
        if (currentlySorting == "max" && list.isNotEmpty()){
            MyAsyncTask().execute()
            sortMyFundraisingByMax()
            adapter.notifyDataSetChanged()
        }else if (list.isNotEmpty()){
            MyAsyncTask().execute()
            sortMyFundraisingByMin()
            adapter.notifyDataSetChanged()
        }
    }

    // Katta Raise bo`yicha tartiblash(sortlash)
    private fun sortMyFundraisingByMax(){
        list.sortByDescending { it.toRaise }
    }

    // Kichik Raise bo`yicha tartiblash(sortlash)
    private fun sortMyFundraisingByMin(){
        list.sortBy { it.toRaise }
    }

    // Ma`lumotlar kelgandan keyin qayta sortlash
    private fun sortingDataWhenCame(){
        if (myBase.getAllSort()[0].sortBy == "max"){
            sortMyFundraisingByMax()
            currentlySorting = "max"
        }else{
            sortMyFundraisingByMin()
            currentlySorting = "min"
        }
    }

    // Avvalgi kodlar
    private fun oldCodes(){
        // database dan olinadigan malumotlar
        /*
         private fun loadUF() : MutableList<MyFundraisingData>{
           //  funds = mutableListOf()
             if (myBase.getAllFundraisingData().isNotEmpty()){
             //    funds.addAll(myBase.getAllFundraisingData())
             }else if (myBase.getAllFundraisingData().isEmpty()){
                 // ma`lumot bo`sh bo`ladi, biron bir narsa yozib qo`yish mumkin,
                 // fund larning xammasi database ga saqlanadi. databasedan olish -> myBase.getAllFundraisingData()
                 // fund-lar list tipida keladi mutablelist ga o`tkazish shart emas ArrayList dan foydalanilgan yaxshiroq
             }

           //  return funds
         }
         */

    }

    // progressBar ni ko`rsatish
    private fun showProgress(){
        binding.progressBar.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.progressBar.setProgress(100,true)
        }
    }

    // progressBarni yo`qotish
    private fun hideProgress(){
        binding.progressBar.visibility = View.GONE
    }

    // firebaseDataBase-dan ma`lumotlarni olish
    private fun getAllDataFromDatabase(){
        list.clear()
        reference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children){
                    val myFundraisingData:MyFundraisingData? = child.getValue(MyFundraisingData::class.java)
                    list.add(MyFundraisingData(myFundraisingData!!.pushKey, myFundraisingData.id,myFundraisingData.title,myFundraisingData.imgId,myFundraisingData.raised,myFundraisingData.toRaise,myFundraisingData.donN,myFundraisingData.daysLeft,myFundraisingData.category,myFundraisingData.nameOfRecipients,myFundraisingData.donationDocuments,myFundraisingData.medicalDocuments,myFundraisingData.story,myFundraisingData.imageLink))
                    Log.d(TAG, "onDataChange: ${myFundraisingData.pushKey}")
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })

    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        sortingOnResume()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.bookmark -> {
                findNavController().navigate(R.id.bookmarkFragment)
            }
            R.id.notification -> {
                findNavController().navigate(R.id.notificationFragment)
            }
            R.id.search ->{
                findNavController().navigate(R.id.searchFragment)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("StaticFieldLeak")
    inner class MyAsyncTask:AsyncTask<Void?,Void?,Void?>(){
        @SuppressLint("ObsoleteSdkInt")
        @Deprecated("Deprecated in Java")
        override fun onPreExecute() {
            showProgress()
            Toast.makeText(requireContext(), "Uploading data...", Toast.LENGTH_SHORT).show()
            super.onPreExecute()
        }
        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg p0: Void?): Void? {
            getAllDataFromDatabase()
            sortingDataWhenCame()
            return null
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            hideProgress()
            refresh()
        }

    }


    // yangilash
    @SuppressLint("NotifyDataSetChanged")
    private fun refresh(){
        adapter = MyFundraisingAdapter(requireContext(),list,object :MyFundraisingAdapter.onEditClick{
            override fun editClick(myFundraisingData: MyFundraisingData) {
                MyEditingFundData.myFundraisingData.pushKey = myFundraisingData.pushKey
                MyEditingFundData.myFundraisingData.id = myFundraisingData.id
                MyEditingFundData.myFundraisingData.title = myFundraisingData.title
                MyEditingFundData.myFundraisingData.toRaise = myFundraisingData.toRaise
                MyEditingFundData.myFundraisingData.raised = myFundraisingData.raised
                MyEditingFundData.myFundraisingData.imageLink = myFundraisingData.imageLink
                MyEditingFundData.myFundraisingData.category = myFundraisingData.category
                MyEditingFundData.myFundraisingData.daysLeft = myFundraisingData.daysLeft
                MyEditingFundData.myFundraisingData.donN = myFundraisingData.donN
                MyEditingFundData.myFundraisingData.donationDocuments = myFundraisingData.donationDocuments
                MyEditingFundData.myFundraisingData.imgId = myFundraisingData.imgId
                MyEditingFundData.myFundraisingData.medicalDocuments = myFundraisingData.medicalDocuments
                MyEditingFundData.myFundraisingData.nameOfRecipients = myFundraisingData.nameOfRecipients
                MyEditingFundData.myFundraisingData.story = myFundraisingData.story
                PushingKeySaver.key = myFundraisingData.pushKey.toString()
                findNavController().navigate(R.id.editMyFundraisingFragment)
            }
        })
        binding.myFundraisingRV.adapter = adapter
        binding.myFundraisingRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.notifyDataSetChanged()
        checkForRefresh()
    }
    // yangilangandan keyingi holat qayta tekshirish internet pastligida foydasi katta
    @SuppressLint("NotifyDataSetChanged")
    private fun checkForRefresh(){
        val handler = Handler()
        handler.postDelayed({
            if (list.isEmpty()){
                showProgress()
                refresh()
            }else{
                hideProgress()
                adapter.notifyDataSetChanged()
            }
        },500)
    }

}