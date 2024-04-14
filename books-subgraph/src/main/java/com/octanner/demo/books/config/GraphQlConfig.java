package com.octanner.demo.books.config;

import com.apollographql.federation.graphqljava.Federation;
import com.octanner.demo.books.books.Book;
import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
