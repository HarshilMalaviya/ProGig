package com.Ntra.ProGig;

import com.Ntra.ProGig.Entity.Role;
import com.Ntra.ProGig.Entity.User;
import com.Ntra.ProGig.Service.FreelancerService;
import com.Ntra.ProGig.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProGigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProGigApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserService Service){
		return  args ->{
			Service.saveUser(new User(null,
						"dhyey","baldha",
						"dhyeybaldha@gmail.com","dhyeybaldha",
						"1234", List.of("'python','java','c'"),
						"i am willing to work in backend field", Role.FREELANCER,null,null));
			Service.saveUser(new User(null,
					"harshil","malaviya",
					"harshilmalaviya@gmail.com","harshilmalaviya",
					"1234", List.of("'python','java','c'"),
					"I hope i would get genuine freelancer", Role.CLIENT,null,null));};
	}

}
