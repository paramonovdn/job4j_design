package ru.job4j.generic;

import java.util.*;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();


    @Override
    public void add(T model) {
        boolean flag = false;
            Collection<T> mapvalues = storage.values();
            for (T mapvalue : mapvalues) {
                if (mapvalue.getId().equals(model.getId())) {
                    flag = true;
                }
            }
            if (!flag) {
                storage.put(model.getId(), model);
            }
    }

    @Override
    public boolean replace(String id, T model) {
        if (storage.containsKey(id)) {
            storage.replace(id, model);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T findById(String id) {
        if (storage.containsKey(id)) {
            return storage.get(id);
        } else {
            return null;
        }
    }

}