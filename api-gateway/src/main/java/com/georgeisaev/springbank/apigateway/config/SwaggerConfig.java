package com.georgeisaev.springbank.apigateway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Gateway", version = "1.0", description = "Documentation APIs v1.0")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {

//    @Bean
//    public List<GroupedOpenApi> apis(RouteDefinitionLocator locator) {
//        List<GroupedOpenApi> apis = Optional.ofNullable(
//                        locator.getRouteDefinitions()
//                                .collectList()
//                                .block())
//                .orElse(Collections.emptyList())
//                .stream()
//                .map(routeDefinition ->
//                        GroupedOpenApi.builder()
//                                .group(routeDefinition.getId())
//                                .pathsToMatch(String.valueOf(routeDefinition.getPredicates()
//                                        .stream()
//                                        .map(PredicateDefinition::getArgs)
//                                        .map(Map::values)
//                                        .flatMap(Collection::stream)
//                                        .map(s -> s.replace("[", ""))
//                                        .map(s -> s.replace("]", ""))
//                                        .map(s -> s + "/**")
//                                        .toList())
//                                )
//                                .build())
//                .toList();
//        return apis;
//    }

}
