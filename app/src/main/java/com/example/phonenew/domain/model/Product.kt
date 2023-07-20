package com.example.phonenew.domain.model

import com.example.phonenew.data.database.entities.ProductEntity
import com.example.phonenew.data.model.ProductModel

data class Product(
    val id : Int,
    val name : String,
    val price : Int,
    val image : String,
    val description : String?,
    val lastPrice : Int?,
    val credit : Boolean?
)

fun ProductModel.toDomain() = Product(id, name, price, image, description, lastPrice, credit)
fun ProductEntity.toDomain() = Product(id, name, price, image, description, lastPrice, credit)