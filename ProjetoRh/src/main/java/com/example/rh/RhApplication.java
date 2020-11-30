package com.example.rh;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RhApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhApplication.class, args);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//		LocalDateTime dateTime1= LocalDateTime.parse("2014-11-25 00:30:00", formatter);
//		LocalDateTime dateTime2= LocalDateTime.parse("2014-11-25 01:30:00", formatter);
//
//		long diffInMilli = java.time.Duration.between(dateTime1, dateTime2).toMillis();
//		long diffInSeconds = java.time.Duration.between(dateTime1, dateTime2).getSeconds();
//		long diffInMinutes = java.time.Duration.between(dateTime1, dateTime2).toMinutes();
//		long diffInHours = java.time.Duration.between(dateTime1, dateTime2).toHours();
//		System.out.println(diffInMinutes);
//		System.out.println(diffInHours);
		
		// sysout + ctrl + espaco
		// CTRL + shift + O. retira importacoes
	}

}
