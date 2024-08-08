package com.abhi.gatewayserver.ConfigurationFiles;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator seriesConfig(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route(
                        p -> p.path("/seriesProject/office/**")
                                .filters( f-> f.rewritePath("/seriesProject/office/(?<segment>.*)","/${segment}")
                                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                                .uri("lb://OFFICE")
                )
//				.route(
//						p -> p.path("/seriesProject/friends/**")
//								.filters( f-> f.rewritePath("/seriesProject/friends/(?<segment>.*)","/${segment}")) //Specified in YAML file
//								.uri("lb://FRIENDS")
//				)
                .route(
                        p-> p.path("/seriesProject/got/**")
                                .filters(f-> f.rewritePath("/seriesProject/got/(?<segment>.*)","/${segment}")
                                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                )
                                .uri("lb://GOT")
                )
                .route(
                        p->p.path("/seriesProject/series/**")
                                .filters(f-> f.rewritePath("/seriesProject/series/(?<segment>.*)","/${segment}")
                                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                                .uri("lb://SERIES")
                )
                .build();
    }


}
