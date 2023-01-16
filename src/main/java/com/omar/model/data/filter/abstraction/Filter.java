package com.omar.model.data.filter.abstraction;

public interface Filter {
  boolean filter(Object collection, Filter filter);
}
