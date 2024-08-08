package com.abhi.gatewayserver.ConfigFiles;

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;

import java.time.LocalDateTime;

@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator seriesConfig(RouteLocatorBuilder builder) {


        return builder.routes()
                .route(
                        p-> p.path("/seriesProject/office/**")
                                .filters(f-> f.rewritePath("/seriesProject/office/(?<segment>.*)","/${segment}")
                                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                        .circuitBreaker( config-> config.setName("officeCircuitBreaker")))
                                .uri("lb://OFFICE")
                )
				.route(
						p -> p.path("/seriesProject/friends/**")
								.filters( f-> f.rewritePath("/seriesProject/friends/(?<segment>.*)","/${segment}")
                                        .circuitBreaker( config-> config.setName("friendsCircuitBreaker")))
								.uri("lb://FRIENDS")
				)
                .route(
                        p-> p.path("/seriesProject/got/**")
                                .filters(f-> f.rewritePath("/seriesProject/got/(?<segment>.*)","/${segment}")
                                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                        .circuitBreaker( config-> config.setName("gotCircuitBreaker")))
                                .uri("lb://GOT")
                )
                .route(
                        p->p.path("/seriesProject/series/**")
                                .filters(f-> f.rewritePath("/seriesProject/series/(?<segment>.*)","/${segment}")
                                        .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                        .circuitBreaker( config-> config.setName("seriesCircuitBreaker")
                                                .setFallbackUri("forward:/contactSupport")))
                                .uri("lb://SERIES")
                )
                .build();

    }

}
