package es.uji.ei1027.reservas;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AreaReservasApplication {

	  private static final Logger log = Logger.getLogger(AreaReservasApplication.class.getName());

	  public static void main(String[] args) {
	     // Auto-configura l'aplicació
	     new SpringApplicationBuilder(AreaReservasApplication.class).run(args);
	  }


}
