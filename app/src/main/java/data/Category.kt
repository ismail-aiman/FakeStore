package data

import com.google.gson.annotations.SerializedName

data class Category(
    val id: Int,
    val name: String,
    val slug: String,
    val image: String,
    val creationAt: String,
    val updatedAt: String
)

data class Product(
    val id: Int,
    val title: String,
    val slug: String,
    val price: Int,
    val description: String,
    val category: Category,
    @SerializedName("images")
    val images: List<String>,
    val creationAt: String,
    val updatedAt: String
)

