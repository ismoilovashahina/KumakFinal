package com.example.projectworkaprilkumak.myfundraising

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.databinding.FragmentEditMyFundraisingBinding
import com.example.projectworkaprilkumak.datas.FundsImage
import com.example.projectworkaprilkumak.datas.MyFundraisingData
import com.example.projectworkaprilkumak.modules.MyEditingFundData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class EditMyFundraisingFragment : Fragment() {
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var firebaseStorage: FirebaseStorage
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var referenceImg: StorageReference
    var imageUrl:String? = null
    private lateinit var toolbar: Toolbar
    private lateinit var binding: FragmentEditMyFundraisingBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEditMyFundraisingBinding.inflate(inflater, container, false)
        toolbar = binding.editToolbar
        val activity : AppCompatActivity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)

        // Tahrirlanayotgan fund ni olish
        getCurrentFundData()

        // surat tahrirlash
        binding.editPicBtn.setOnClickListener {
            getNewImage()
        }
        insFireDatabases()

        val categoryList = listOf("Education","Environment","Social","Sick Child","Medical","Infrastructure","Art","Disaster","Orphanage","Disable","Humanity","Others")
        // array ichidagi kategoriyalar kelmadi listOf dan foydalanildi
        val adapter = ArrayAdapter(requireContext(),R.layout.dropdown_category,categoryList)
        adapter.notifyDataSetChanged()
        binding.editCategoryView.setAdapter(adapter)

        binding.editTextInputDropDown.setEndIconOnClickListener {
            // ikonka bosilganda kategoriyalar chiqadi
            binding.editCategoryView.showDropDown()
        }

        binding.editDonationProposalDoc.setOnClickListener {
            showFileChooser()
        }
        binding.editMedDoc.setOnClickListener {
            showFileChooser()
        }
        binding.editContinueBtn.setOnClickListener {
            checkAndSendData()
        }

        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }


        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun getCurrentFundData() {
        Glide.with(requireContext().applicationContext)
            .load(MyEditingFundData.myFundraisingData.imageLink)
            .fitCenter() // surat o`rtasini hisobga olgan holda
            .diskCacheStrategy(DiskCacheStrategy.ALL) // keshga saqlash
            .placeholder(R.drawable.photo)
            .into(binding.editPic)

        binding.editTotalDon.setText(MyEditingFundData.myFundraisingData.donN.toString())
        binding.editStory.setText(MyEditingFundData.myFundraisingData.story.toString())
        binding.editCategoryView.setText(MyEditingFundData.myFundraisingData.category.toString())
        binding.editMedDoc.setText(MyEditingFundData.myFundraisingData.medicalDocuments.toString())
        binding.editDonationProposalDoc.setText(MyEditingFundData.myFundraisingData.donationDocuments.toString())
        binding.editExpireDate.setText(MyEditingFundData.myFundraisingData.daysLeft.toString())
        binding.editRecipientsName.setText(MyEditingFundData.myFundraisingData.nameOfRecipients.toString())
        binding.editTitle.setText(MyEditingFundData.myFundraisingData.title.toString())
        binding.editUsagePlan.setText("Free")
        imageUrl = MyEditingFundData.myFundraisingData.imageLink
    }

    // firebaseDatabaseni instlizatsiya
    private fun insFireDatabases(){
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("funds")
        firebaseStorage = FirebaseStorage.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()
        referenceImg = firebaseStorage.getReference("images")
    }

    // firebasega surat va ma`lumotlarni yuborish
    private fun checkAndSendData(){
        val fundsImage = FundsImage(imageUrl!!)
        firebaseFirestore.collection("images")
            .add(fundsImage)
            .addOnSuccessListener {
                // surat to`liq o`zgarganida
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Error while uploading image, try again!", Toast.LENGTH_SHORT).show()
            }
        val myFundraisingData = MyFundraisingData(MyEditingFundData.myFundraisingData.pushKey.toString(),1,binding.editTitle.text.toString(),1,55,binding.editTotalDon.text.toString().toInt(),binding.editTotalDon.text.toString().toInt(),binding.editExpireDate.text.toString().toInt(),binding.editCategoryView.text.toString(),binding.editRecipientsName.text.toString(),binding.editDonationProposalDoc.text.toString(),binding.editMedDoc.text.toString(),binding.editStory.text.toString(),imageUrl)
        reference.child(MyEditingFundData.myFundraisingData.pushKey.toString()).setValue(myFundraisingData).addOnCompleteListener {
            Toast.makeText(requireContext(), "Successfully edited!", Toast.LENGTH_SHORT).show()
        }
        clearViews()
    }

    // yozilganlarni tozalash
    private fun clearViews() {
        binding.editTitle.text!!.clear()
        binding.editTotalDon.text!!.clear()
        binding.editExpireDate.text!!.clear()
        binding.editRecipientsName.text!!.clear()
        binding.editDonationProposalDoc.text!!.clear()
        binding.editMedDoc.text!!.clear()
        binding.editStory.text!!.clear()
    }
    // suratni olish uchun ImageContent
    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()){ uri ->
            uri ?: return@registerForActivityResult
            // surat uchun nom
            val title = System.currentTimeMillis()
            // suratni yuklab olish telefon xotirasidan uri orqali
            try {
                val uploadTask = referenceImg.child(title.toString()).putFile(uri!!)
                Toast.makeText(requireContext(), "Image uploading!", Toast.LENGTH_SHORT).show()

                uploadTask.addOnSuccessListener {
                    val downloadUrl = it.metadata?.reference?.downloadUrl
                    downloadUrl?.addOnSuccessListener {imagaUri ->
                        binding.editPic.setImageURI(uri)
                        imageUrl = imagaUri.toString()
                        Toast.makeText(requireContext(), "Photo successfully editing!", Toast.LENGTH_SHORT).show()
                    }
                }
                    .removeOnFailureListener {
                        Toast.makeText(requireContext(), "Photo unsuccessfully editing!", Toast.LENGTH_SHORT).show()
                    }
            }catch (e:Exception){
                e.printStackTrace()
                Toast.makeText(requireContext(), "Cancelled editing image!", Toast.LENGTH_SHORT).show()
            }

        }

    // yangi surat olish uchun
    private fun getNewImage() {
        getImageContent.launch("image/*")
    }

    // fayl tanlash uchun
    private fun showFileChooser() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        try {
            startActivityForResult(Intent.createChooser(intent, "Select a file"), 100)
        } catch (exception: Exception){
            Toast.makeText(requireContext(), "Please install a file manager.", Toast.LENGTH_SHORT).show()
        }
    }

}