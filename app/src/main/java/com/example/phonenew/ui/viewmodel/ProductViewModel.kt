package com.example.phonenew.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonenew.domain.GetProductDetailUseCase
import com.example.phonenew.domain.GetProductsUseCase
import com.example.phonenew.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {
    // Se conecta con la activity
    val productModel = MutableLiveData<List<Product>>()
    val productDetail = MutableLiveData<Product>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getProductsUseCase()

            if (result.isNotEmpty()) {
                productModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun getProductDetail(id : Int) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getProductDetailUseCase(id)

            if (result.id != -1) {
                productDetail.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}