package ufcg.psoft.projetofinal.ProjetoFinalPSoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import ufcg.psoft.projetofinal.ProjetoFinalPSoft.rest.filters.TokenFilter;

@SpringBootApplication
public class ProjetoFinalPSoftApplication {

	@Bean
	public FilterRegistrationBean filterJwt() {
		FilterRegistrationBean filterRb = new FilterRegistrationBean();
		filterRb.setFilter(new TokenFilter());
		filterRb.addUrlPatterns("/private");
		return filterRb;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetoFinalPSoftApplication.class, args);
	}

}
