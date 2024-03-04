package org.tolga.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tolga.dto.request.CreateProductRequestDto;
import org.tolga.dto.request.UpdateProductRequestDto;
import org.tolga.repository.entity.Product;
import org.tolga.service.ProductService;

import java.util.List;

import static org.tolga.constant.ApiUrls.*;


@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(CREATE_PRODUCT)
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequestDto dto){
        return ResponseEntity.ok(productService.createProduct(dto));
    }
    @PatchMapping(UPDATE_PRODUCT)
    public ResponseEntity<String> updateProduct(@RequestBody UpdateProductRequestDto dto){
        return ResponseEntity.ok(productService.updateProduct(dto));
    }
    @DeleteMapping(DELETE_PRODUCT+"/{id}")
    public ResponseEntity<String> deletedProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
    @GetMapping(GET_PRODUCT+"/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }
    @GetMapping(GET_ALL_PRODUCTS)
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

}
