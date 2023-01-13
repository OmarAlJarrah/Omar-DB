package com.omar.model.access.abstraction;

import com.omar.model.access.impl.PermissionStatus;
import com.omar.model.db.abstraction.Table;


public interface Permission {
  PermissionStatus getReadPermission();

  PermissionStatus getWritePermission();

  Table getResource();
}
