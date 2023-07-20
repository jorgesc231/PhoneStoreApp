package com.example.phonenew.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.phonenew.domain.model.Product

@Entity(tableName = "products_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "price") val price : Int,
    @ColumnInfo(name = "image") val image : String,
    @ColumnInfo(name = "description") val description : String?,
    @ColumnInfo(name = "lastPrice") val lastPrice : Int?,
    @ColumnInfo(name = "credit") val credit : Boolean?,
)

fun Product.toDatabase() = ProductEntity(id, name, price, image, description, lastPrice, credit)