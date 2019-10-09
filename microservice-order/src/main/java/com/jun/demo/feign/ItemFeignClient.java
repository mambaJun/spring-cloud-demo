package com.jun.demo.feign;

import com.jun.demo.entity.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Jun
 * @date: 2019/10/9 11:27
 */
@FeignClient(value = "jun-microservice-item")// 申明这是一个Feign客户端，并且指明服务id
public interface ItemFeignClient {

    // 这里定义了类似于 Spring MVC用法的方法，就可以进行RESTful的调用
    @GetMapping("/item/{id}")
    public Item queryItemById(@PathVariable("id") Long id);

}
