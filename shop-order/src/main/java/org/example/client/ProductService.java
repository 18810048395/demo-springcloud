package org.example.client;

import org.example.client.fallback.ProductServiceFallback;
import org.example.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-product",fallback = ProductServiceFallback.class)
public interface ProductService {
    //商品信息查询
    @RequestMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid);
}
