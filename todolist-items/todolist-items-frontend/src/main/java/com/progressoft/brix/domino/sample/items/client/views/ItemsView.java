package com.progressoft.brix.domino.sample.items.client.views;

import com.progressoft.brix.domino.api.client.mvp.view.View;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;

import java.util.List;
import java.util.function.Consumer;

import static com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext.LayoutContent;


public interface ItemsView extends View{
    void showAdd();

    LayoutContent getContent();

    void addNewItemHandler(NewItemHandler newItemHandler);

    void addItem(String title, String description, boolean done, SuccessAddHandler successAddHandler);

    void remove(TodoItem item);

    void onItemStateChanged(Consumer<TodoItem> changeHandler);

    @FunctionalInterface
    interface NewItemHandler {
        void onNewItem(String title, String description);
    }

    interface SuccessAddHandler {
        void onSuccess(TodoItem item);
    }
}