package com.portfolio.taskmanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "Task Manager API", version = "1.0", description = "API de gestión de tareas"),
    servers = {
        @Server(url = "https://upgraded-fortnight-wrxxvv4wxr7f699-8080.app.github.dev", description = "Servidor Codespaces remoto")
    }
)
public class OpenApiConfig {
}