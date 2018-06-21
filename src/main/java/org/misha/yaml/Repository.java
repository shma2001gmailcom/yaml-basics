package org.misha.yaml;

import java.util.List;

public interface Repository<T> {

    T findById(String id);

    List<T> findAll();

    void save(T value);
}

