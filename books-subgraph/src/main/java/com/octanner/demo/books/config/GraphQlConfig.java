package com.octanner.demo.books.config;

import com.apollographql.federation.graphqljava.Federation;
import com.octanner.demo.books.books.Book;
import java.util.Map;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GraphQlConfig {
  @Bean
  public GraphQlSourceBuilderCustomizer bookSourceBuilderCustomizer() {
    return builder ->
        builder.schemaFactory(
            (registry, wiring) ->
                Federation.transform(registry, wiring)
                    .fetchEntities(env -> null)
                    .resolveEntityType(env -> null)
                    .build());
  }
}
