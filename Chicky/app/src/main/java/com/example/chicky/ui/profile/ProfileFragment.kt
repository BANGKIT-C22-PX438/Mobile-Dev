package com.example.chicky.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chicky.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var fstore:FirebaseFirestore
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]
        auth = FirebaseAuth.getInstance()
        fstore = FirebaseFirestore.getInstance()
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val document = auth.currentUser?.let { fstore.collection("users").document(it.uid) }
        document?.get()?.addOnSuccessListener {
            binding.userEmail.text = it.getString("email")
            binding.userPhone.text = it.getString("phone")
            binding.textView9.text= it.getString("fname")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}