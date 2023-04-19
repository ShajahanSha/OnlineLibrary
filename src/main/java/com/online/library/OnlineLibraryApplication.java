package com.online.library;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Shajahan.Shaik on 04/18/2023.
 */

@Slf4j
@SpringBootApplication
public class OnlineLibraryApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OnlineLibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
