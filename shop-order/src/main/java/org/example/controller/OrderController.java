package org.example.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ProductService;
import org.example.domain.Order;
import org.example.domain.Product;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/order/message1")
    public String message1() {
        return "message1";
    }

    //下单
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);

        /**
         * 默认restTemplate使用方式
         */
//        Product product =
//                restTemplate.getForObject("http://localhost:8081/product/" + pid, Product.class);

        /**
         * 配合restTemplate的@LoadBalanced可实现负载均衡
         */
//        Product product =
//                restTemplate.getForObject("http://service-product:8081/product/" + pid, Product.class);
        /**
         * 使用fegin
         */
        Product product = productService.product(pid);


        log.info("查询到{}号商品的信息,内容是:{}", pid, JSON.toJSONString(product));

        //下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);

        orderService.createOrder(order);

        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));

        return order;
    }
}
