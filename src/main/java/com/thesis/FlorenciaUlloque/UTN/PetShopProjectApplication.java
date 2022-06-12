package com.thesis.FlorenciaUlloque.UTN;

import com.thesis.FlorenciaUlloque.UTN.Configurations.UsuarioLogueado;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PetShopProjectApplication implements WebMvcConfigurer {

	public void addViewController(ViewControllerRegistry registry){
		registry.addViewController("index2").setViewName("index2");
	}

	public static void main(String[] args) {
		SpringApplication.run(PetShopProjectApplication.class, args);



	}


}
