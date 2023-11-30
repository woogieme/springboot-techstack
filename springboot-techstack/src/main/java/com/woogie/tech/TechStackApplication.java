package com.woogie.tech;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import com.woogie.tech.core2.config.AppV1Config;

@Import(AppV1Config.class)
@SpringBootApplication (scanBasePackages = "com.woogie.tech.core2.section4")
public class TechStackApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext appContext;
	public static void main(String[] args) {
		SpringApplication.run(TechStackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String[] beans = appContext.getBeanDefinitionNames();
		Arrays.sort(beans);

		for(String bean : beans){
			System.out.println(bean);
		}
	}
}
