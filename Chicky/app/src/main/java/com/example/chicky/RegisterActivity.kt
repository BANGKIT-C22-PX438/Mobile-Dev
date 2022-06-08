package com.example.chicky

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.chicky.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var fstore :FirebaseFirestore
    private lateinit var userID:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        fstore = FirebaseFirestore.getInstance()
        binding.button.setOnClickListener {
            val fullname = binding.fullname.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val phonenumber = binding.fullpassword.toString().trim()
            val confirmpassword = binding.confirmpassword.text.toString().trim()
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                binding.email.error="Email non valid"
                binding.email.requestFocus()
                return@setOnClickListener
            }
            register(fullname,email,phonenumber,confirmpassword)
        }
        binding.textView5.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun register(fullname:String,email:String,phonenumber:String,confirmPassword:String)
    {
        auth.createUserWithEmailAndPassword(email,confirmPassword)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful)
                {
                    userID = auth.currentUser!!.uid
                    val documentReference = fstore.collection("users").document(userID)
                    val user: MutableMap<String, Any> = HashMap()
                    user["fname"] = fullname
                    user["email"] = email
                    user["phone"] = phonenumber
                    user["password"] = confirmPassword
                    documentReference.set(user).addOnSuccessListener {
                        Log.d("Message","DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    Intent(this,LoginActivity::class.java).let {
                        startActivity(it)
                        finish()
                    }
                }
                else
                {
                    val message= task.exception.toString().split(":").toTypedArray()[1]
                    with(binding.tvAlert){
                        text = message
                        visibility = View.VISIBLE
                    }
                }
            }
    }
}