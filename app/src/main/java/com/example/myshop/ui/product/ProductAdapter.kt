package com.example.myshop.ui.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.R
import com.example.myshop.data.model.ProductModel
import com.example.myshop.databinding.ItemEmptyBinding
import com.example.myshop.databinding.ItemLoadingBinding
import com.example.myshop.databinding.ItemProductBinding
import com.squareup.picasso.Picasso

class ProductAdapter(private val productCallback: ProductViewHolder.Callback) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var productItems = emptyList<ProductModel>()
    private lateinit var state: State

    @SuppressLint("NotifyDataSetChanged")
    fun setProduct(productItems: List<ProductModel>) {
        this.productItems = productItems
        state = State.DISPLAY_ITEMS
        notifyDataSetChanged()
    }

    fun productItemRemoved(position: Int) {
        notifyItemRemoved(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun startFullLoading() {
        state = State.LOADING
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == TYPE_PRODUCT) {
            val binding = ItemProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            ProductViewHolder(binding, productCallback)
        } else if (viewType == TYPE_EMPTY) {
            val binding = ItemEmptyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            EmptyViewHolder(binding)
        } else {
            val binding = ItemLoadingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            LoadingViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (productItems.isNotEmpty()) (holder as? ProductViewHolder)?.bind(productItems[position])
    }

    override fun getItemCount(): Int =
        if (productItems.isEmpty()) 1
        else productItems.size

    override fun getItemViewType(position: Int): Int {
        if (state == State.LOADING) return TYPE_LOADING
        if (productItems.isEmpty()) return TYPE_EMPTY

        return TYPE_PRODUCT
    }

    abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class ProductViewHolder(
        private val binding: ItemProductBinding,
        private val callback: Callback
    ) : ViewHolder(binding.root) {

        fun bind(product: ProductModel) {

            binding.tvTitle.text = product.productName
            binding.tvBody.text = product.productBrand

            binding.root.setOnLongClickListener {
                callback.onLongPress(product)
                true
            }

            Picasso.get()
                .load(product.productImage)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.ivPhoto)
        }

        interface Callback {
            fun onLongPress(product: ProductModel)
        }
    }

    class EmptyViewHolder(binding: ItemEmptyBinding) : ViewHolder(binding.root)

    class LoadingViewHolder(binding: ItemLoadingBinding) : ViewHolder(binding.root)

    enum class State { LOADING, DISPLAY_ITEMS }

    companion object {
        private const val TYPE_PRODUCT = 0
        private const val TYPE_EMPTY = 1
        private const val TYPE_LOADING = 2
    }
}