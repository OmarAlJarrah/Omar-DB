package com.omar.database.implementation;

import com.omar.database.abstraction.Database;
import com.omar.model.data.filter.abstraction.Filter;
import com.omar.model.database.abstraction.Table;

import java.util.List;
import java.util.Optional;

public class OmarDB implements Database {
  private List<Table> tables;


  @Override
  public boolean write(Object data) {
    return false;
  }

  @Override
  public void fromSchema(Object schema) {

  }

  @Override
  public Optional<List<Object>> readBy(Object filer) {
    return Optional.empty();
  }

  @Override
  public Optional<List<Object>> readByAllOf(List<Filter> filters) {
    return Optional.empty();
  }

  @Override
  public Optional<List<Object>> readAnyOf(List<Filter> filters) {
    return Optional.empty();
  }

  @Override
  public Optional<Object> readById(Long id) {
    return Optional.empty();
  }

  @Override
  public List<Table> getTables() {
    return null;
  }
}
