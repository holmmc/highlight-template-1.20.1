package com.holmityd.hightlight.events;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public final class EventFactory {
    private static final Set<Event<?>> EVENTS = Collections.synchronizedSet(new HashSet<>());

    private EventFactory() {}

    public static void invalidate() {
        EVENTS.forEach(Event::update);
    }

    public static <T> Event<T> create(Class<? super T> type, Function<List<T>, T> invokerFactory) {
        Event<T> event = new Event<>(type, invokerFactory);
        EVENTS.add(event);
        return event;
    }
}