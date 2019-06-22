package ufcg.psoft.projetofinal.ProjetoFinalPSoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProjetoFinalPSoftApplication {
	
	public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/v1/auth/register").allowedOrigins("*");
            }
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(ProjetoFinalPSoftApplication.class, args);
	}

}
