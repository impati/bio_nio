package com.example.impati.model.property;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Properties {

    private final Map<Class<? extends Property>, Property> map = new HashMap<>();

    public <P extends Property> void add(P property) {
        Class<? extends Property> key = property.getClass().asSubclass(Property.class);
        map.put(key, property);
    }

    // raw type/unchecked 제거: Class#cast 사용
    public <P extends Property> Optional<P> find(Class<P> type) {
        Property value = map.get(type);
        return Optional.ofNullable(type.cast(value));
    }

    public <P extends Property> P getOrThrow(Class<P> type) {
        return find(type).orElseThrow(() -> new NoSuchElementException(type.getName()));
    }

}
