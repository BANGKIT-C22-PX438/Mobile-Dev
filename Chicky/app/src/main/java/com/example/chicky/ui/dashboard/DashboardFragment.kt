package com.example.chicky.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chicky.R
import com.example.chicky.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private lateinit var rvArticle : RecyclerView
    private var _binding: FragmentDashboardBinding? = null
    private val list = ArrayList<Article>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rvArticle = binding.recycleView
        rvArticle.setHasFixedSize(true)
        val name = resources.getStringArray(R.array.Title)
        val url = resources.getStringArray(R.array.link)
        for (i in name.indices)
        {
            val article = Article(name[i],url[i])
            list.add(article)
        }
        showRecyclerList()
        return root
    }
    private fun showRecyclerList()
    {
        rvArticle.layoutManager = LinearLayoutManager(requireContext())
        val listArticleAdapter = ExploreAdapter(list)
        rvArticle.adapter= listArticleAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}