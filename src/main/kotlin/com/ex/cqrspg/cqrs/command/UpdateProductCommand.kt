package com.ex.cqrspg.cqrs.command

data class UpdateProductCommand(
    val id: Long,
    val name: String? = null,
    val description: String? = null,
    val price: Int? = null,
    val stockQuantity: Int? = null
) {
}