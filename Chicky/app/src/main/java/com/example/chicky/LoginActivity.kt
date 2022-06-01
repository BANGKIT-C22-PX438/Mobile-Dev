package com.example.chicky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chicky.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.textView7.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.buttonlogin.setOnClickListener {
            val email = binding.emails.text.toString().trim()
            val password = binding.passwords.text.toString().trim()
            userLogin(email,password)
        }
    }
    private fun userLogin(email:String,password:String)
    {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful)
                {
                    Intent(this,MainActivity::class.java).let {
                        startActivity(it)
                        finish()
                    }
                }
                else
                {
                    val message = task.exception.toString().split(":").toTypedArray()[1]

                }
            }
    }
}