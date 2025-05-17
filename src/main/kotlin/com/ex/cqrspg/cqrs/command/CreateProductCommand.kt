package com.ex.cqrspg.cqrs.command

data class CreateProductCommand(
    val name: String,
    val description: String,
    val price: Int,
    val stockQuantity: Int
) {

}
