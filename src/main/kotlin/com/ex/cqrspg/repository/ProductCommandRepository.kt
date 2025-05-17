package com.ex.cqrspg.repository

import com.ex.cqrspg.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductCommandRepository: JpaRepository<ProductEntity, Long> {
    fun findAllBySyncedToReadModelFalse(): List<ProductEntity>
}