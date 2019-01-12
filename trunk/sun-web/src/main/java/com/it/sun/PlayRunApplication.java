package com.it.sun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 程序的入口
 */
@SpringBootApplication
@EnableScheduling		//启用定时器的注解
@EnableAsync			//开启程序异步调用功能
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) //停止扫描数据库配置
public class PlayRunApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayRunApplication.class, args);
	}
}
