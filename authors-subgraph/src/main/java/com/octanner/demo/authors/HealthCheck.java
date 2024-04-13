package com.octanner.demo.authors;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HealthCheck {
  @QueryMapping
  public Boolean healthCheck() {
    return true;
  }
}
