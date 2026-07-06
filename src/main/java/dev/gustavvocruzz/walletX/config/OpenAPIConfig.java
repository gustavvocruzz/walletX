package dev.gustavvocruzz.walletX.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("WalletX API")
                        .description("A comprehensive digital wallet management system API. This project provides endpoints for user management and wallet operations.")
                        .version("0.0.1-SNAPSHOT")
                        .contact(new Contact()
                                .name("Gustavo Cruz")
                                .email("contact@gustavvocruzz.dev")
                                .url("https://github.com/gustavvocruzz/walletX"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development Server"),
                        new Server()
                                .url("https://api.walletx.com")
                                .description("Production Server")
                ));
    }
}
