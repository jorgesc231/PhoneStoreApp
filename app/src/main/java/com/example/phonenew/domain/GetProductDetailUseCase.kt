package com.example.phonenew.domain

import com.example.phonenew.data.ProductRepository
import com.example.phonenew.data.database.entities.toDatabase
import com.example.phonenew.domain.model.Product
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(product_id : Int) : Product {
        val local_product = repository.getProductFromDatabase(product_id)

        return if (local_product.description != null && local_product.lastPrice != null && local_product.credit != null) {
            local_product
        } else {
            val remote_product = repository.getProductFromApi(product_id)
            repository.insertProduct(remote_product.toDatabase())
            remote_product
        }
    }
}