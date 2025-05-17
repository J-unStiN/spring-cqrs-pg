package com.ex.cqrspg.cqrs.query

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "product_views")
data class ProductView(
    @Id
    val id: String,

    val productId: Long,
    val name: String,
    val description: String,
    val price: Int,
    val stockQuantity: Int,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
