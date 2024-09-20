package com.Ntra.ProGig;

import com.Ntra.ProGig.Entity.UserRole;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProGigApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProGigApplication.class, args);
	}



}
