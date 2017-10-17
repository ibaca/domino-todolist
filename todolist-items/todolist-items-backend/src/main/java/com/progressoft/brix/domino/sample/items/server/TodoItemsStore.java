package com.progressoft.brix.domino.sample.items.server;

import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class TodoItemsStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoItemsStore.class);
    private static final Map<String, TodoItem> items = new LinkedHashMap<>();

    public static TodoItem get(String key) {
        return items.get(key);
    }

    public static void store(String key, TodoItem item) {
        items.put(key, item);
        LOGGER.info("item with key " + key + " is stored [" + item.getItemTitle() + "," + item.getItemDescription() + "]");
    }

    public static Collection<TodoItem> all() {
        return items.values();
    }

    public static void clear() {
        items.clear();
    }

    public static void clearDone() {
        items.values().stream().filter(TodoItem::isDone).forEach(item -> items.remove(item.getItemTitle()));
    }
}
