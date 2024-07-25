package com.Ntra.ProGig;

import com.Ntra.ProGig.Entity.Freelancer;
import com.Ntra.ProGig.Service.FreelancerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class ProGigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProGigApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(FreelancerService Service){
		return  args ->{
			Service.saveUser(new Freelancer(null,
						"dhyey","baldha",
						"dhyeybaldha@gmail.com","dhyeybaldha",
						"1234", List.of("'python','java','c'"),
						"i am willing to work in backend field","Freelancer"));};
	}

}
