package com.example.myshop.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.data.Resource
import com.example.myshop.data.model.ProductModel
import com.example.myshop.data.usecases.GetProducts
import com.example.myshop.di.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProducts: GetProducts,
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {

    private val _productItems = MutableLiveData<Resource<MutableList<ProductModel>>>()
    val productItems: LiveData<Resource<MutableList<ProductModel>>> get() = _productItems

    private val _itemRemoved = MutableLiveData<Int>()
    val itemRemoved: LiveData<Int> get() = _itemRemoved

    fun onViewCreated() {
        _productItems.postValue(Resource.loading())
        loadItems()
    }

    fun onScreenRefresh() {
        loadItems()
    }

    fun onProductRemoved(product: ProductModel) {
        val items = _productItems.value?.data ?: return
        val position = items.indexOfFirst {
            it.id == product.id
        }

        items.remove(product)
        _itemRemoved.value = position
    }

    private fun loadItems() {
        viewModelScope.launch(dispatchersProvider.io) {
            val items = getProducts.call().toMutableList()

            withContext(dispatchersProvider.main) {
                _productItems.postValue(Resource.success(items))
            }
        }
    }
}
