package com.jfsd.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.jfsd.travel")
public class TravelBuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelBuddyApplication.class, args);
		System.out.println("Travel Buddy");
	}

}
