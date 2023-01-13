package com.omar.model.access.abstraction;

import com.omar.model.access.implementation.PermissionStatus;
import com.omar.model.database.abstraction.Table;


public interface Permission {
  PermissionStatus getReadPermission();

  PermissionStatus getWritePermission();

  Table getResource();
}
