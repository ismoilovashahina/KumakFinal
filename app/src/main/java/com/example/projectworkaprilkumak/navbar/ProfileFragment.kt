package com.example.projectworkaprilkumak.navbar

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.projectworkaprilkumak.database.MyBase
import com.example.projectworkaprilkumak.databinding.FragmentProfileBinding
import com.example.projectworkaprilkumak.datas.MyProfie
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ProfileFragment : Fragment() {
    lateinit var myBase: MyBase
    var currentId = 0
    var currentImagePath = ""
    var currentByteArray:ByteArray?  =null
    var userId = 0
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        var toolbar: Toolbar = binding.toolbar
        val activity : AppCompatActivity = this.activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowTitleEnabled(true)

        myBase = MyBase(requireContext())

        myBase.getUser().forEach { user ->
                myBase.getProfile().forEach {
                    if (it.userId == user.id){
                        setProfileImage(it.imagePath!!,it.image!!)
                        currentImagePath = it.imagePath!!
                        currentByteArray = it.image!!
                        currentId = it.id!!
                        userId = it.userId!!
                        binding.profileName.setText(it.fullName)
                        binding.profileEmail.setText(it.email)
                        binding.profilePhoneNumber.setText(it.phoneN)
                        binding.profileGender.setText(it.gender)
                        binding.profileCity.setText(it.city)
                    }
                }
        }

        // profile ma`lumotlari o`zgarishiga avtomatik saqlanadi!
        binding.profileName.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkAll()){
                    myBase.editProfile(MyProfie(currentId,userId,binding.profileName.text.toString(),binding.profileEmail.text.toString(),binding.profilePhoneNumber.text.toString(),binding.profileGender.text.toString(),binding.profileCity.text.toString(),currentImagePath,currentByteArray))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.profileEmail.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkAll()){
                    myBase.editProfile(MyProfie(currentId,userId,binding.profileName.text.toString(),binding.profileEmail.text.toString(),binding.profilePhoneNumber.text.toString(),binding.profileGender.text.toString(),binding.profileCity.text.toString(),currentImagePath,currentByteArray))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.profilePhoneNumber.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkAll()){
                    myBase.editProfile(MyProfie(currentId,userId,binding.profileName.text.toString(),binding.profileEmail.text.toString(),binding.profilePhoneNumber.text.toString(),binding.profileGender.text.toString(),binding.profileCity.text.toString(),currentImagePath,currentByteArray))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.profileGender.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkAll()){
                    myBase.editProfile(MyProfie(currentId,userId,binding.profileName.text.toString(),binding.profileEmail.text.toString(),binding.profilePhoneNumber.text.toString(),binding.profileGender.text.toString(),binding.profileCity.text.toString(),currentImagePath,currentByteArray))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.profileCity.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkAll()){
                    myBase.editProfile(MyProfie(currentId,userId,binding.profileName.text.toString(),binding.profileEmail.text.toString(),binding.profilePhoneNumber.text.toString(),binding.profileGender.text.toString(),binding.profileCity.text.toString(),currentImagePath,currentByteArray))
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        binding.resetProfileImg.setOnClickListener {
            getNewImage()
        }


        return binding.root
    }
    private  fun checkAll():Boolean{
        if (binding.profileCity.text!!.isNotEmpty() && binding.profileEmail.text!!.isNotEmpty() && binding.profilePhoneNumber.text!!.isNotEmpty() && binding.profileGender.text!!.isNotEmpty() && binding.profileName.text!!.isNotEmpty()) return true
        else return false
    }
    // eski kodlar pastda
    /*
     var gson = Gson()
        var interestList = ArrayList<SelectInterest>()
        var type = object : TypeToken<List<Profile>>(){}.type
        var currently = ""



         interestList = gson.fromJson(currently, type)

        interestList.forEach {
            binding.profileName.setText(it.fullN)
            binding.profileEmail.setText(it.email)
            binding.profilePhoneNumber.setText(it.phoneN)
            binding.profileGender.setText(it.gender)
            binding.profileCity.setText(it.city)
        }
     */

    private fun setProfileImage(path:String,byte:ByteArray){
        binding.profileImg.setImageURI(Uri.parse(path))
        val bitmap = BitmapFactory.decodeByteArray(byte,0,byte.size)
        binding.profileImg.setImageBitmap(bitmap)
    }
    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()){
            it ?: return@registerForActivityResult
            binding.profileImg.setImageURI(it)
            val inputStream = requireContext().contentResolver?.openInputStream(it)
            val file = File(requireContext().filesDir,"image.jpg")
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream?.close()
            val absoulut = file.absolutePath

            val fileInputStream = FileInputStream(file)
            val readBytes = fileInputStream.readBytes()
            currentImagePath= absoulut
            currentByteArray = readBytes
        }

    private fun getNewImage() {
        getImageContent.launch("image/*")
    }


}