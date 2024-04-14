package com.octanner.demo.books.config;

import com.apollographql.federation.graphqljava.Federation;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.ClassNameTypeResolver;

@Configuration(proxyBeanMethods = false)
public class GraphQlConfig {
  @Bean
  public GraphQlSourceBuilderCustomizer bookSourceBuilderCustomizer(
      BookFederatedEntityResolver entityResolver) {
    return builder ->
        builder.schemaFactory(
            (registry, wiring) ->
                Federation.transform(registry, wiring)
                    .fetchEntities(entityResolver::resolveEntities)
                    .resolveEntityType(new ClassNameTypeResolver())
                    .build());
  }
}
