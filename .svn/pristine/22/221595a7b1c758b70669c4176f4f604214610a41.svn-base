package com.archimedes.portal.web;

import com.archimedes.service.product.api.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Wing
 * @Date: 2018/12/6 下午2:48
 */

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    final IProductService iProductService;
    @RequestMapping("/getProduct1")
    public String getProduct(){
        log.info("getProduct");
        return iProductService.getProduct("222");
    }
}
