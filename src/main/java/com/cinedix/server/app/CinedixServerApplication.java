package com.cinedix.server.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinedixServerApplication implements CommandLineRunner {

	/*
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	*/
	public static void main(String[] args) {
		SpringApplication.run(CinedixServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*
		String password = "12345";
		for (int i = 0; i < 2; i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}
		*/
	}

}
