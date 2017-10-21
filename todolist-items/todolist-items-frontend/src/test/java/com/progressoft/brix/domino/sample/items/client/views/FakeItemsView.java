package com.progressoft.brix.domino.sample.items.client.views;

import com.progressoft.brix.domino.api.client.annotations.UiView;
import com.progressoft.brix.domino.sample.items.client.presenters.ItemsPresenter;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext;

import java.util.HashMap;
import java.util.Map;

@UiView(presentable = ItemsPresenter.class)
public class FakeItemsView implements ItemsView {

    private Map<String, TodoItem> items = new HashMap<>();

    private LayoutContext.LayoutContent layoutContent= () -> null;
    private boolean addDialogOpen;
    private ItemsUiHandlers uiHandlers;

    @Override
    public void setUiHandlers(ItemsUiHandlers uiHandlers) {
        this.uiHandlers=uiHandlers;
    }

    @Override
    public void showAddDialog() {
        this.addDialogOpen=true;
    }

    @Override
    public LayoutContext.LayoutContent getContent() {
        return layoutContent;
    }


    @Override
    public void addItem(String title, String description, boolean done) {
        FakeTodoItem todoItem = new FakeTodoItem(title, description, done);
        items.put(title, todoItem);
        uiHandlers.onSuccessCreation(todoItem);
    }

    @Override
    public void remove(TodoItem item) {
        items.remove(item.getItemTitle());
    }

    public void toggle(String title) {
        uiHandlers.onItemStateChanged(getItem(title));
    }

    public void addNewItem(String title, String description) {
        uiHandlers.onNewItem(title, description);
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

    public ItemsUiHandlers getUiHandlers() {
        return uiHandlers;
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
