package com.example.avito.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return loadOpenAPIFromYaml();
    }

    private OpenAPI loadOpenAPIFromYaml() {
        try {
            Yaml yaml = new Yaml();
            try (InputStream inputStream = new ClassPathResource("openapi.yaml").getInputStream()) {
                Map<String, Object> yamlMap = yaml.load(inputStream);

                OpenAPI openAPI = new OpenAPI().info(convertInfo((Map<String, Object>) yamlMap.get("info")));

                // Convert paths
                Paths paths = new Paths();
                Map<String, Map<String, Object>> pathsMap = (Map<String, Map<String, Object>>) yamlMap.get("paths");
                for (Map.Entry<String, Map<String, Object>> entry : pathsMap.entrySet()) {
                    PathItem pathItem = new Yaml().loadAs(new Yaml().dump(entry.getValue()), PathItem.class);
                    paths.addPathItem(entry.getKey(), pathItem);
                }
                openAPI.setPaths(paths);

                // Convert components
                Map<String, Object> componentsMap = (Map<String, Object>) yamlMap.get("components");
                Components components = new Components();
                Map<String, Schema> schemas = (Map<String, Schema>) componentsMap.get("schemas");
                components.setSchemas(schemas);
                openAPI.setComponents(components);

                return openAPI;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load OpenAPI YAML file", e);
        }
    }

    private Info convertInfo(Map<String, Object> infoMap) {
        Info info = new Info();
        info.setTitle((String) infoMap.get("title"));
        info.setDescription((String) infoMap.get("description"));
        info.setVersion((String) infoMap.get("version"));
        return info;
    }
}




