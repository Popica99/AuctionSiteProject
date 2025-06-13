package sdacademy.auctionsiteproject.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // permite toate rutele
                .allowedOrigins("http://localhost:4200") // ✅ adaugă URL-ul frontendului
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true); // ✅ permite trimiterea de credentiale (ex: Authorization)
    }
}
