package com.omar.db.impl;

import com.omar.db.abstraction.Database;
import com.omar.model.data.filter.abstraction.Filter;
import com.omar.model.db.abstraction.Table;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

  public Optional<String> delete(UUID id){return Optional.empty();}
}
