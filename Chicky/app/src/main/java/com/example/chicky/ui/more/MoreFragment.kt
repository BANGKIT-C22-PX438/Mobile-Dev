package com.example.chicky.ui.more

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chicky.R
import com.example.chicky.databinding.FragmentMoreBinding
import com.example.chicky.databinding.FragmentProfileBinding

class MoreFragment : Fragment() {

    companion object {
        fun newInstance() = MoreFragment()
    }
    private var _binding: FragmentMoreBinding? = null
    private lateinit var viewModel: MoreViewModel
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}