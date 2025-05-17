package com.ex.cqrspg.service

import com.ex.cqrspg.cqrs.query.ProductView
import com.ex.cqrspg.repository.ProductQueryRepository
import org.springframework.stereotype.Service

@Service
class ProductQueryService(
    private val productViewRepository: ProductQueryRepository
) {

    fun findProductById(productId: Long): ProductView? {
        return productViewRepository.findByProductId(productId)
    }

    fun findAllProducts(): List<ProductView> {
        return productViewRepository.findAll()
    }


}