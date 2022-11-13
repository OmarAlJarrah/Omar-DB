package com.omar.model.database.implementation;

import graphql.schema.GraphQLSchema;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class Schema {
  private GraphQLSchema schema;

  private Schema() { }

  @NotNull
  public static Schema fromGraphQLSchema(GraphQLSchema graphQLSchema) {
    Schema instance = new Schema();
    instance.schema = graphQLSchema;

    return instance;
  }
}
