package com.example.chicky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chicky.databinding.ActivityEditProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding:ActivityEditProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var fstore:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        fstore = FirebaseFirestore.getInstance()
        val document = auth.currentUser?.let { fstore.collection("users").document(it.uid) }
        document?.get()?.addOnSuccessListener {
            binding.editText.setText(it.getString("fname"))
            binding.editText3.setText(it.getString("phone"))
            binding.editText4.setText(it.getString("email"))

        }
    }
}