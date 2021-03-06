package org.example.dao;

import org.example.entity.Operation;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

@Component
public class InMemoryOperationStorage implements OperationStorage {

    private final Map<String, List<Operation>> map = new HashMap<>();

    public InMemoryOperationStorage() {
    }

    @Override
    public boolean save(Operation operation) {
        if (map.containsKey(operation.getUser().getLogin())) {
            List<Operation> operations = map.get(operation.getUser().getLogin());
            operations.add(operation);
        } else {
            map.put(operation.getUser().getLogin(), new ArrayList<>());
            List<Operation> operations = map.get(operation.getUser().getLogin());
            operations.add(operation);
        }
        return true;
    }

    @Override
    public List<Operation> findAllByUser(String username) {
        return new ArrayList<>(map.get(username));
    }

    @Override
    public List<Operation> findAll() {
        return new ArrayList(map.values());
    }
}
