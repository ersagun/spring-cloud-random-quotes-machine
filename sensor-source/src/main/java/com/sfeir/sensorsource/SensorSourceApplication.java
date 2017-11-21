package com.sfeir.sensorsource;

import com.sfeir.sensorsource.channel.SourceChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
public class SensorSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorSourceApplication.class, args);
	}
}
