package org.example.dao;

import org.example.entity.Operation;

import java.util.List;

public interface OperationStorage {
    boolean save(Operation operation);

    List<Operation> findAllByUser(String username);

    List<Operation> findAll();
}
