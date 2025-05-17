package com.ex.cqrspg.controller

import com.ex.cqrspg.cqrs.command.CreateProductCommand
import com.ex.cqrspg.cqrs.command.UpdateProductCommand
import com.ex.cqrspg.entity.ProductEntity
import com.ex.cqrspg.service.ProductCommandService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductCommandController(private val productService: ProductCommandService) {

    @PostMapping
    fun createProduct(@RequestBody command: CreateProductCommand): ResponseEntity<ProductEntity> {
        val product = productService.createProduct(command)
        return ResponseEntity.ok(product)
    }

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody command: UpdateProductCommand
    ): ResponseEntity<ProductEntity> {
        val updatedCommand = command.copy(id = id)
        val product = productService.updateProduct(updatedCommand)
        return ResponseEntity.ok(product)
    }


}