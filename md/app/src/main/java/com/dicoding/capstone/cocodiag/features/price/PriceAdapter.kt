package com.dicoding.capstone.cocodiag.features.price

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.capstone.cocodiag.R
import com.dicoding.capstone.cocodiag.databinding.ItemArticleBinding
import com.dicoding.capstone.cocodiag.databinding.ItemPriceBinding


class PriceAdapter(private val priceItem: PriceItem) :
    RecyclerView.Adapter<PriceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPriceBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(priceItem)
    }

    override fun getItemCount() = 1

    inner class ViewHolder(private val binding: ItemPriceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(priceItem: PriceItem) {
            binding.realPrice.text = priceItem.price
            binding.regionTv.text = priceItem.region
            binding.dateTv.text = priceItem.date
        }
    }
}
