package com.oswizar.springbootsample.controller;

import cn.hutool.core.util.IdUtil;
import com.oswizar.springbootsample.entity.Product;
import com.oswizar.springbootsample.model.ResponseResult;
import com.oswizar.springbootsample.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/createProduct")
    public ResponseResult createProduct(@RequestBody Product product) {
        // 如果前端没有传 id，自动生成一个 Long 类型的 ID（雪花算法）
        if (product.getId() == null) {
            product.setId(String.valueOf(IdUtil.getSnowflake(1L, 1L).nextId()));
        }
        product.setCreated_at(Instant.now());
        Product savedProduct = productService.saveProduct(product);
        return ResponseResult.success(savedProduct);
    }

    @GetMapping("/getAllProducts")
    public ResponseResult getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseResult.success(products);
    }

    @GetMapping("/{id}")
    public ResponseResult getProductById(@PathVariable String id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseResult::success).orElseGet(() -> ResponseResult.fail(404, "商品不存在"));
    }

    @GetMapping("/category/{category}")
    public ResponseResult getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseResult.success(products);
    }

    @GetMapping("/search")
    public ResponseResult searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return ResponseResult.success(products);
    }

    @PutMapping("/updateProduct")
    public ResponseResult updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.saveProduct(product);
        return ResponseResult.success(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseResult.success("删除成功", null);
    }
}
