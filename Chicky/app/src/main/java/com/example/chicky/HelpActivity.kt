package com.example.chicky

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chicky.databinding.ActivityHelpBinding
import com.google.android.gms.common.wrappers.Wrappers.packageManager


class HelpActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHelpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Help"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.emailus.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND )
            intent.putExtra(Intent.EXTRA_EMAIL,"c22px438@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT,"Help problem with apps")
            startActivity(Intent.createChooser(intent,"select your email app"))
        }
        binding.callus.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","081322794085",null))
            startActivity(intent)
        }
        binding.chatwithus.setOnClickListener {
            val intent =Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:" + "081322794085"))
            startActivity(Intent.createChooser(intent,"Send Message"))
        }

    }
}