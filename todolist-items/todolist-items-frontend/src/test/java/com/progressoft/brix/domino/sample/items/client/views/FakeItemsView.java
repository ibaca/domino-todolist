package com.progressoft.brix.domino.sample.items.client.views;

import com.progressoft.brix.domino.api.client.annotations.UiView;
import com.progressoft.brix.domino.sample.items.client.presenters.ItemsPresenter;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@UiView(presentable = ItemsPresenter.class)
public class FakeItemsView implements ItemsView {

    //    public static class
    private NewItemHandler addNewItemHandler;
    private Consumer<TodoItem> onStateChangeHandler;
    private Map<String, TodoItem> items = new HashMap<>();

    private LayoutContext.LayoutContent layoutContent= () -> null;
    private boolean addDialogOpen;

    @Override
    public void showAdd() {
        this.addDialogOpen=true;
    }

    @Override
    public LayoutContext.LayoutContent getContent() {
        return layoutContent;
    }

    @Override
    public void addNewItemHandler(NewItemHandler newItemHandler) {
        this.addNewItemHandler = newItemHandler;
    }

    @Override
    public void addItem(String title, String description, boolean done, SuccessAddHandler successAddHandler) {
        FakeTodoItem todoItem = new FakeTodoItem(title, description, done);
        items.put(title, todoItem);
        successAddHandler.onSuccess(todoItem);
    }

    @Override
    public void remove(TodoItem item) {
        items.remove(item.getItemTitle());
    }

    @Override
    public void onItemStateChanged(Consumer<TodoItem> changeHandler) {
        this.onStateChangeHandler = changeHandler;
    }

    public NewItemHandler getAddNewItemHandler() {
        return addNewItemHandler;
    }

    public Consumer<TodoItem> getOnStateChangeHandler() {
        return onStateChangeHandler;
    }

    public void toggle(String title) {
        onStateChangeHandler.accept(getItem(title));
    }

    public void addNewItem(String title, String description) {
        addNewItemHandler.onNewItem(title, description);
    }

    public TodoItem getItem(String title) {
        return items.get(title);
    }

    public boolean isAddDialogOpen() {
        return addDialogOpen;
    }

    public Map<String, TodoItem> getItems() {
        return items;
    }

    private class FakeTodoItem implements TodoItem {

        private String title;
        private String description;
        private boolean done;

        public FakeTodoItem(String title, String description, boolean done) {
            this.title = title;
            this.description = description;
            this.done = done;
        }

        @Override
        public boolean isDone() {
            return done;
        }

        @Override
        public String getItemTitle() {
            return title;
        }

        @Override
        public String getItemDescription() {
            return description;
        }

        @Override
        public void toggle() {
            this.done = !done;
        }
    }
}
