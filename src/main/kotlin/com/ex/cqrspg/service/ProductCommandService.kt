package com.ex.cqrspg.service

import com.ex.cqrspg.cqrs.command.CreateProductCommand
import com.ex.cqrspg.cqrs.command.UpdateProductCommand
import com.ex.cqrspg.entity.ProductEntity
import com.ex.cqrspg.repository.ProductCommandRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ProductCommandService(
    private val productCommandRepository: ProductCommandRepository
) {

    @Transactional
    fun createProduct(command: CreateProductCommand): ProductEntity {
        val product = ProductEntity(
            name = command.name,
            description = command.description,
            price = command.price,
            stockQuantity = command.stockQuantity
        )
        return productCommandRepository.save(product)
    }

    @Transactional
    fun updateProduct(command: UpdateProductCommand): ProductEntity {
        val findProduct = productCommandRepository.findById(command.id)
            .orElseThrow { IllegalArgumentException("Product with id ${command.id} does not exist") }

        findProduct.apply {
            command.name?.let { findProduct.name = it }
            command.description?.let { findProduct.description = it }
            command.price?.let { findProduct.price = it }
            command.stockQuantity?.let { findProduct.stockQuantity = it }

            updatedAt = LocalDateTime.now()
            syncedToReadModel = false
        }

        return findProduct;
    }





}