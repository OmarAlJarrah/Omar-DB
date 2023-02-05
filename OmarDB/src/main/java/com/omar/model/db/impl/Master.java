package com.omar.model.db.impl;

import java.util.List;

public record Master(String port, List<Slave> slaves) {
}
