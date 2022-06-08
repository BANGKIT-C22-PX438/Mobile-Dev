package com.example.chicky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chicky.databinding.ActivityAppSettingBinding

class AppSettingActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAppSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ID.setOnClickListener {
            Toast.makeText(this@AppSettingActivity,"Sorry, We still working on this feature",Toast.LENGTH_LONG).show()
        }
        binding.EN.setOnClickListener {
            Toast.makeText(this@AppSettingActivity,"Sorry, We still working on this feature",Toast.LENGTH_LONG).show()
        }
    }
}