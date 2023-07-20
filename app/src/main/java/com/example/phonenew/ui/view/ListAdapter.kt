package com.example.phonenew.ui.view

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phonenew.R
import com.example.phonenew.databinding.ListItemBinding
import com.example.phonenew.domain.model.Product
import java.util.Locale


class ListAdapter (private var productList : List<Product> = emptyList(), private val onItemSelected : (product : Product) -> Unit) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    companion object {
        val number_format = NumberFormat.getNumberInstance(Locale.ITALIAN)
    }

    class ListViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)

        fun bind(product : Product, onItemSelected : (Product) -> Unit) {
            //binding.tvId.text = product.id.toString()
            binding.tvName.text = product.name
            binding.tvPrice.text = itemView.context.getString(R.string.price_format_with_dollar_sign, number_format.format(product.price))

            Glide.with(binding.root)
                .load(product.image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivProductImage)

            binding.root.setOnClickListener {
                onItemSelected(product)
            }
        }
    }

    fun updateList(productList : List<Product>) {
        this.productList = productList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(productList[position], onItemSelected)
    }
}