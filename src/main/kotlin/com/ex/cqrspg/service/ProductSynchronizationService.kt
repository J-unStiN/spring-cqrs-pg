package com.ex.cqrspg.service

import com.ex.cqrspg.cqrs.query.ProductView
import com.ex.cqrspg.repository.ProductCommandRepository
import com.ex.cqrspg.repository.ProductQueryRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ProductSynchronizationService(
    private val productCommandRepository: ProductCommandRepository,
    private val productQueryRepository: ProductQueryRepository
) {

    @Scheduled(fixedRate = 5000)
    @Transactional
    fun synchronizeProducts() {
        val unsyncedProducts = productCommandRepository.findAllBySyncedToReadModelFalse()

        unsyncedProducts.forEach { product ->
            // 기존 조회 모델 확인
            val existingView = productQueryRepository.findByProductId(product.id!!)

            val productView = existingView?.copy(
                name = product.name,
                description = product.description,
                price = product.price,
                stockQuantity = product.stockQuantity,
                updatedAt = product.updatedAt
            ) ?: ProductView(
                id = UUID.randomUUID().toString(),
                productId = product.id!!,
                name = product.name,
                description = product.description,
                price = product.price,
                stockQuantity = product.stockQuantity,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt
            )

            // MongoDB에 저장
            productQueryRepository.save(productView)

            // 동기화 완료 표시
            product.syncedToReadModel = true
            productCommandRepository.save(product)
        }

    }

}