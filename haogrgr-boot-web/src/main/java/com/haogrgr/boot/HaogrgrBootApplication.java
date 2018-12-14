package com.haogrgr.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.haogrgr.boot.dal.mapper")
public class HaogrgrBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaogrgrBootApplication.class, args);
	}

}

