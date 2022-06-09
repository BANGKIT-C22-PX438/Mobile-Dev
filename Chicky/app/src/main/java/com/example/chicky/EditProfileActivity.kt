package com.example.chicky

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chicky.databinding.ActivityEditProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding:ActivityEditProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var fstore:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Edit Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)+1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        binding.calendaricon.setOnClickListener {
            val datePickerDialog = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                DatePickerDialog(this@EditProfileActivity, { _, mYear, mMonth, mDay ->
                    val months = mMonth+1
                    binding.editText2.setText("$mDay/$months/$mYear")
                },year,month,day)
            } else {
                TODO()
            }
            datePickerDialog.show()
        }
        auth = FirebaseAuth.getInstance()
        fstore = FirebaseFirestore.getInstance()
        val document = auth.currentUser?.let { fstore.collection("users").document(it.uid) }
        document?.get()?.addOnSuccessListener {
            binding.editText.setText(it.getString("fname"))
            binding.editText3.setText(it.getString("phone"))
            binding.editText4.setText(it.getString("email"))
            if(it.getString("radio") == "Female")
            {
                binding.radioButton2.isChecked = true
            }
            else
            {
                binding.radioButton.isChecked = true
            }
            binding.editText2.setText(it.getString("birthday"))
        }
        val fauth = auth.currentUser
        binding.button2.setOnClickListener {
            fauth?.updateEmail(binding.editText4.text.toString())?.addOnSuccessListener {
                val docRef = fstore.collection("users").document(fauth.uid)
                val edit : MutableMap<String,Any> = HashMap()
                edit["email"] = binding.editText4.text.toString()
                edit["phone"] = binding.editText3.text.toString()
                edit["fname"] = binding.editText.text.toString()
                if (binding.radioButton.isChecked)
                {
                    edit["radio"] = binding.radioButton.text.toString()
                }
                else
                {
                    edit["radio"] = binding.radioButton2.text.toString()
                }
                edit["birthday"] = binding.editText2.text.toString()
                docRef.update(edit)
                startActivity(Intent(this@EditProfileActivity,MainActivity::class.java))
            }
        }

    }
}