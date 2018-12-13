package com.archimedes.service.product.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Wing
 * @Date: 2018/12/6 下午2:33
 */
@FeignClient(name = "product-server")
public interface IProductService {

    @RequestMapping("/getProduct")
    public String getProduct(@RequestParam("productNo") String productNo);
}
