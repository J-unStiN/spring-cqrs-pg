package com.ex.cqrspg.controller

import com.ex.cqrspg.cqrs.query.ProductView
import com.ex.cqrspg.service.ProductQueryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/product-views")
class ProductQueryController(
    private val productQueryService: ProductQueryService
) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductView>> {
        val products = productQueryService.findAllProducts()
        return ResponseEntity.ok(products)
    }

    @GetMapping("/{productId}")
    fun getProductById(@PathVariable productId: Long): ResponseEntity<ProductView> {
        val product = productQueryService.findProductById(productId)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(product)
    }


}