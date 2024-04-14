package com.octanner.demo.authors.config;

import com.apollographql.federation.graphqljava.Federation;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GraphQlConfig {
  @Bean
  public GraphQlSourceBuilderCustomizer authorSourceBuilderCustomizer() {
    return builder ->
        builder.schemaFactory(
            (registry, wiring) ->
                Federation.transform(registry, wiring)
                    .fetchEntities(env -> null)
                    .resolveEntityType(env -> null)
                    .build());
  }
}
