package com.bcp.postulacion.tipoCambioService;

import com.bcp.postulacion.tipoCambioService.dao.TipoCambioDao;
import com.bcp.postulacion.tipoCambioService.dao.UsuarioDao;
import com.bcp.postulacion.tipoCambioService.model.TipoCambio;
import com.bcp.postulacion.tipoCambioService.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class TipoCambioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipoCambioServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(TipoCambioDao ta, UsuarioDao us, BCryptPasswordEncoder encoder) {
		return args -> {
			ta.save(new TipoCambio("DOL", "SOL",
					new BigDecimal(3.5), LocalDateTime.now(), null));
			us.save(new Usuario("admin", encoder.encode("123")));
		};
	}


}
