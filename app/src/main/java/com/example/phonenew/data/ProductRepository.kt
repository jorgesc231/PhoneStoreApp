package com.example.phonenew.data

import com.example.phonenew.data.database.dao.ProductDao
import com.example.phonenew.data.database.entities.ProductEntity
import com.example.phonenew.data.network.ProductService
import com.example.phonenew.domain.model.Product
import com.example.phonenew.domain.model.toDomain
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api : ProductService,
    private val productDao: ProductDao
) {

    suspend fun getAllProductsFromApi() : List<Product> {
        val response = api.getProducts()
        return response.map { it.toDomain() }
    }

    suspend fun getAllProductsFromDatabase() : List<Product> {
        val response = productDao.getAllProducts()

        return response.map { it.toDomain() }
    }

    suspend fun insertProducts(products : List<ProductEntity>) {
        productDao.insertAll(products)
    }

    suspend fun insertProduct(product: ProductEntity) {
        productDao.insertProduct(product)
    }

    suspend fun getProductFromApi(id : Int) : Product {
        val response = api.getProductDetail(id)
        return response.toDomain()
    }

    suspend fun getProductFromDatabase(id : Int) : Product {
        val response = productDao.getProduct(id)

        return response.toDomain()
    }
}