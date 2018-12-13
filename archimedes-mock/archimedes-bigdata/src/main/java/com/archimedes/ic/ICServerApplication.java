package com.archimedes.ic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.archimedes.ic.mapper"})
public class ICServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ICServerApplication.class, args);
	}
}
