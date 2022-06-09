package com.example.chicky.ui.dashboard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chicky.WebActivity
import com.example.chicky.databinding.ItemRowArticleBinding

class ExploreAdapter(private val listArticle: ArrayList<Article>) : RecyclerView.Adapter<ExploreAdapter.ListViewHolder>() {
    class ListViewHolder(private val binding: ItemRowArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        var tvName = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
       return ListViewHolder(
           ItemRowArticleBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           )
       )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name,url) = listArticle[position]
        holder.tvName.text = name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,WebActivity::class.java)
            intent.putExtra(WebActivity.URL,url)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listArticle.size


}