package com.example.myshop.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myshop.data.Resource
import com.example.myshop.data.model.ProductModel
import com.example.myshop.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    private val viewModel: ProductViewModel by viewModels()

    private val productAdapter = ProductAdapter(object : ProductAdapter.ProductViewHolder.Callback {
        override fun onLongPress(product: ProductModel) {
            viewModel.onProductRemoved(product)
        }

    })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        binding.rvProducts.apply {
            this.layoutManager = layoutManager
            this.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
            this.adapter = productAdapter
        }

        viewModel.productItems.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> productAdapter.startFullLoading()
                Resource.Status.SUCCESS -> {
                    productAdapter.setProduct(it.data.orEmpty())
                    stopRefreshingLoading()
                }
                Resource.Status.ERROR -> {
                    productAdapter.setProduct(emptyList())
                    stopRefreshingLoading()
                }

            }
        }

        viewModel.itemRemoved.observe(viewLifecycleOwner) {
            productAdapter.productItemRemoved(it)
        }

        viewModel.onViewCreated()

        binding.swiperefresh.setOnRefreshListener {
            viewModel.onScreenRefresh()
        }

    }

    private fun stopRefreshingLoading(){
        if (binding.swiperefresh.isRefreshing) {
            binding.swiperefresh.isRefreshing = false
        }
    }
}