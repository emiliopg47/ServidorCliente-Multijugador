package com.emilio.servidor_multijugador;

import com.emilio.servidor_multijugador.web.websocket.data.PongRoom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class ServidorApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServidorApplication.class, args);
	}

}
