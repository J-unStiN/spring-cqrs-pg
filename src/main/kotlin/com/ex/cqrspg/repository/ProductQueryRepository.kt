package com.ex.cqrspg.repository

import com.ex.cqrspg.cqrs.query.ProductView
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductQueryRepository: MongoRepository<ProductView, String> {
    fun findByProductId(productId: Long): ProductView?

}