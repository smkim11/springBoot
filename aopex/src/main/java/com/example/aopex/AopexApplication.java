package com.example.aopex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ServletComponentScan // 필터
public class AopexApplication implements WebMvcConfigurer{

	private AdminInterceptor adminInterceptor;
	public AopexApplication(AdminInterceptor adminInterceptor) {
		this.adminInterceptor = adminInterceptor;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AopexApplication.class, args);
	}

	// 인터셉터
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/*");
		
		WebMvcConfigurer.super.addInterceptors(registry);
		
	}
}
