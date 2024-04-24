package com.example.projectworkaprilkumak.myfundraising

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
import com.example.projectworkaprilkumak.R
import com.example.projectworkaprilkumak.databinding.FragmentCreateNewFundRaisingBinding
import com.example.projectworkaprilkumak.datas.FundsImage
import com.example.projectworkaprilkumak.datas.MyFundraisingData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class CreateNewFundRaisingFragment : Fragment() {
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var firebaseStorage: FirebaseStorage
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var referenceImg: StorageReference
    var imageUrl:String? = null
    private lateinit var binding: FragmentCreateNewFundRaisingBinding
    private lateinit var toolbar: Toolbar
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCreateNewFundRaisingBinding.inflate(inflater, container, false)
        toolbar = binding.toolbar
        val activity : AppCompatActivity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)

        // surat tanlash
        binding.addPicBtn.setOnClickListener {
            getNewImage()
        }
        insFireDatabases()


        val categoryList = listOf("Education","Environment","Social","Sick Child","Medical","Infrastructure","Art","Disaster","Orphanage","Disable","Humanity","Others")
        // array ichidagi kategoriyalar kelmadi listOf dan foydalanildi
        val adapter = ArrayAdapter(requireContext(),R.layout.dropdown_category,categoryList)
        adapter.notifyDataSetChanged()
        binding.categoryView.setAdapter(adapter)
        binding.textInputDropDown.setEndIconOnClickListener {
            // ikonka bosilganda kategoriyalar chiqadi
            binding.categoryView.showDropDown()
        }
        binding.donationProposalDoc.setOnClickListener {
            showFileChooser()
        }
        binding.medDoc.setOnClickListener {
            showFileChooser()
        }
        binding.continueBtn.setOnClickListener {
              checkAndSendData()
           }
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        return binding.root

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
                // surat to`liq joylanganda ishlaydi
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Error while uploading image, try again!", Toast.LENGTH_SHORT).show()
            }
        var key = reference.push().key
        val myFundraisingData = MyFundraisingData(key,1,binding.title.text.toString(),1,55,binding.totalDon.text.toString().toInt(),binding.totalDon.text.toString().toInt(),binding.expireDate.text.toString().toInt(),binding.categoryView.text.toString(),binding.recipientsName.text.toString(),binding.donationProposalDoc.text.toString(),binding.medDoc.text.toString(),binding.story.text.toString(),imageUrl)
        reference.child(key!!).setValue(myFundraisingData).addOnCompleteListener {
            Toast.makeText(requireContext(), "Successfully sent!", Toast.LENGTH_SHORT).show()
        }
        clearViews()
    }
    // yozilganlarni tozalash
    private fun clearViews(){
        binding.title.text!!.clear()
        binding.totalDon.text!!.clear()
        binding.expireDate.text!!.clear()
        binding.recipientsName.text!!.clear()
        binding.donationProposalDoc.text!!.clear()
        binding.medDoc.text!!.clear()
        binding.story.text!!.clear()
    }
    // suratni olish uchun ImageContent
    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()){uri ->
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
                        binding.addPic.setImageURI(uri)
                        imageUrl = imagaUri.toString()
                        Toast.makeText(requireContext(), "Photo successfully sent!", Toast.LENGTH_SHORT).show()
                    }
                }
                    .removeOnFailureListener {
                        Toast.makeText(requireContext(), "Photo unsuccessfully sent!", Toast.LENGTH_SHORT).show()
                    }
            }catch (e:Exception){
                e.printStackTrace()
                Toast.makeText(requireContext(), "Cancelled sending image!", Toast.LENGTH_SHORT).show()
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


    private fun oldCodes(){
        // databasega rasm saqlash uchun yozilgan kod
        /*
         binding.addPic.setImageURI(it)
                val inputStream = requireContext().contentResolver?.openInputStream(it)
                val file = File(requireContext().filesDir,"image.jpg")
                val fileOutputStream = FileOutputStream(file)
                inputStream?.copyTo(fileOutputStream)
                inputStream?.close()
                fileOutputStream?.close()
                val absoulut = file.absolutePath

                val fileInputStream = FileInputStream(file)
                val readBytes = fileInputStream.readBytes()
                imageUser = ImageUser(absoulut,readBytes)
         */
    }
}


