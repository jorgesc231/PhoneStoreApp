package com.example.phonenew.domain

import com.example.phonenew.data.ProductRepository
import com.example.phonenew.data.database.entities.toDatabase
import com.example.phonenew.domain.model.Product
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke() : List<Product> {
        val local_products = repository.getAllProductsFromDatabase()

        return if (local_products.isNotEmpty()) {
            local_products
        } else {
            val remote_products = repository.getAllProductsFromApi()
            repository.insertProducts(remote_products.map { it.toDatabase() })
            remote_products
        }
    }

}