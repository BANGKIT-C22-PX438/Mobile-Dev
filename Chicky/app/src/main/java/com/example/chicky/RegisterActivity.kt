package com.example.chicky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import com.example.chicky.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.button.setOnClickListener {
            val fullname = binding.fullname.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val password = binding.fullpassword.toString().trim()
            val confirmpassword = binding.confirmpassword.text.toString().trim()
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                binding.email.error="Email non valid"
                binding.email.requestFocus()
                return@setOnClickListener
            }
            register(fullname,email,password,confirmpassword)
        }
        binding.textView5.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun register(fullname:String,email:String,password:String,confirmPassword:String)
    {
        auth.createUserWithEmailAndPassword(email,confirmPassword)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful)
                {
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