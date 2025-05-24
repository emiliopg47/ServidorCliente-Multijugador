package com.emilio.servidor_multijugador;

import com.emilio.servidor_multijugador.persistencia.servicios.ServiceRanking;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServidorApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServidorApplication.class, args);
	}

}
