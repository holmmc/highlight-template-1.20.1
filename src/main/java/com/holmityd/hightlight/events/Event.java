package com.holmityd.hightlight.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Event<T> {
    private final Function<List<T>, T> invokerFactory;
    private final List<T> listeners = new ArrayList<>();
    private volatile T invoker;

    public Event(Class<? super T> type, Function<List<T>, T> invokerFactory) {
        this.invokerFactory = invokerFactory;
        update();
    }

    public T invoker() {
        return invoker;
    }

    void update() {
        this.invoker = invokerFactory.apply(listeners);
    }

    public void register(T listener) {
        Objects.requireNonNull(listener, "Tried to register a null listener!");
        listeners.add(listener);
        update();
    }

    public int listenerCount() {
        return listeners.size();
    }
}