package com.omar.model.access.impl;

import com.omar.model.access.abstraction.Permission;
import com.omar.model.db.abstraction.DataCollection;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ReadWritePermissions implements Permission {
  private final PermissionStatus readPermission;
  private final PermissionStatus writePermission;
  private final DataCollection resource;


  // Constructors
  private ReadWritePermissions(PermissionStatus readPermission, PermissionStatus writePermission, DataCollection resource) {
    this.readPermission = readPermission;
    this.writePermission = writePermission;
    this.resource = resource;
  }


  @NotNull
  @Contract(value = "_ -> new", pure = true)
  public static ReadWritePermissions defaultPermissions(DataCollection resource) {
    return new ReadWritePermissions(PermissionStatus.DENIED, PermissionStatus.DENIED, resource);
  }

  // Getters
  public PermissionStatus getReadPermission() {
    return readPermission;
  }

  @Override
  public PermissionStatus getWritePermission() {
    return writePermission;
  }

  @Override
  public DataCollection getResource() {
    return resource;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ReadWritePermissions other) {
      return other.readPermission.equals(this.readPermission) &&
          other.resource.getName().equals(this.resource.getName());
    }
    return false;
  }
}
