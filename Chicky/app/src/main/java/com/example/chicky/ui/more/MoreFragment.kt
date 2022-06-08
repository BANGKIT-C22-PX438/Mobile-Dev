package com.example.chicky.ui.more

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.chicky.*
import com.example.chicky.databinding.FragmentMoreBinding
import com.example.chicky.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class MoreFragment : Fragment() {

    companion object {
        fun newInstance() = MoreFragment()
    }
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentMoreBinding? = null
    private lateinit var viewModel: MoreViewModel
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        val root: View = binding.root
        auth =FirebaseAuth.getInstance()
        binding.gotohelp.setOnClickListener {
            val intent = Intent(requireActivity(),HelpActivity::class.java)
            startActivity(intent)
        }
        binding.gotoappsetting.setOnClickListener {
            val intent = Intent(requireActivity(),AppSettingActivity::class.java)
            startActivity(intent)
        }
        binding.gotoeditprofile.setOnClickListener {
            val intent = Intent(requireActivity(),EditProfileActivity::class.java)
            startActivity(intent)
        }
        binding.textView14.setOnClickListener {
            auth.signOut()
            val intent = Intent(requireActivity(),LoginActivity::class.java)
            startActivity(intent)
        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}