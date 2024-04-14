package com.octanner.demo.books.config;

import com.apollographql.federation.graphqljava._Entity;
import com.octanner.demo.books.books.Book;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookFederatedEntityResolver {
  public static final String _TYPENAME = "__typename";

  public <E> CompletableFuture<List<E>> resolveEntities(DataFetchingEnvironment environment) {
    List<Integer> ids = getIdsFromRepresentations(environment);
    if (ids.isEmpty()) {
      return CompletableFuture.completedFuture(List.of());
    }
    DataLoader<Integer, E> dataLoader = environment.getDataLoader(Book.class.getName());
    return dataLoader.loadMany(ids);
  }

  private List<Integer> getIdsFromRepresentations(DataFetchingEnvironment environment) {
    List<Map<String, Object>> representations = environment.getArgument(_Entity.argumentName);
    return representations.stream()
        .filter(it -> Book.TYPE_NAME.equals(it.get(_TYPENAME)))
        .map(
            values ->
                Optional.ofNullable(values.get(Book.PRIMARY_KEY))
                    .map(Object::toString)
                    .map(Integer::parseInt))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .toList();
  }
}
