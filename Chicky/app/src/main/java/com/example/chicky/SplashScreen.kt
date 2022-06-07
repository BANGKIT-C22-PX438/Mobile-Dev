package com.example.chicky

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class SplashScreen : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        auth = FirebaseAuth.getInstance()
        val currentuser = auth.currentUser
        Handler(Looper.getMainLooper()).postDelayed({
            if(currentuser==null){
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        },3000)
    }
}