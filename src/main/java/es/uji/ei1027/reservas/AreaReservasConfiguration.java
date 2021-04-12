package es.uji.ei1027.reservas;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AreaReservasConfiguration {
	// Configura l'accés a la base de dades (DataSource)
	// a partir de les propietats a src/main/resources/applications.properties
	// que comencen pel prefix spring.datasource
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

}
