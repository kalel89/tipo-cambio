package com.bcp.postulacion.tipoCambioService;

import com.bcp.postulacion.tipoCambioService.dao.TipoCambioDao;
import com.bcp.postulacion.tipoCambioService.model.TipoCambio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class TipoCambioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipoCambioServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(TipoCambioDao ta) {
		return args -> {
			ta.save(new TipoCambio("DOL", "SOL",
					new BigDecimal(3.5), LocalDateTime.now(), null));
		};
	}


}
