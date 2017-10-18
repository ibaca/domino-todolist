package com.progressoft.brix.domino.sample.items.client.presenters;

import com.progressoft.brix.domino.api.client.annotations.Presenter;
import com.progressoft.brix.domino.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.brix.domino.sample.items.client.requests.*;
import com.progressoft.brix.domino.sample.items.client.views.ItemsView;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.progressoft.brix.domino.sample.items.shared.response.LoadItemsResponse.Item;
import static com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext.LayoutMenuItem;


@Presenter
public class DefaultItemsPresenter extends BaseClientPresenter<ItemsView> implements ItemsPresenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultItemsPresenter.class);

    private List<TodoItem> addedItems = new ArrayList<>();

    @Override
    public void initView(ItemsView view) {
        view.addNewItemHandler(this::addItem);
        view.onItemStateChanged(todoItem -> {
            new ToggleItemServerRequest(todoItem.getItemTitle()).send();
        });
    }

    private void addItem(String title, String description) {
        view.addItem(title, description, false, item -> new AddItemServerRequest(item).send());
    }

    @Override
    public void contributeToLayoutModule(LayoutContext context) {

        new LoadItemsServerRequest().send();
        context.setContent(view.getContent());

        context.addMenuItem(new MenuItem("delete", "Clear All", () -> {
            context.closeMenu();
            clearAll();
        }));
        context.addMenuItem(new MenuItem("clear", "Clear Done", () -> {
            context.closeMenu();
            removeDoneItems();
        }));

        context.addMenuItem(new MenuItem("settings", "Settings", () -> LOGGER.info("Settings")));
        context.addMenuItem(new MenuItem("help", "Help", () -> LOGGER.info("help")));

        context.setAddHandler(() -> view.showAdd());

    }

    @Override
    public void onItemAdded(TodoItem item) {
        addedItems.add(item);
    }

    @Override
    public void onItemsLoaded(List<Item> items) {
        items.forEach(item -> view.addItem(item.getItemTitle(), item.getItemDescription(), item.isDone(), addedItem -> addedItems.add(addedItem)));
    }

    @Override
    public void onItemsCleared(boolean cleared) {
        if (cleared) {
            view.clearAll();
            addedItems.clear();
        } else
            LOGGER.error("Error while clearing all items");
    }

    private void clearAll() {
        new ClearAllServerRequest().send();
    }

    private void removeDoneItems() {
        new ClearDoneServerRequest().send();
    }

    public void onDoneCleared() {
        List<TodoItem> doneItems = addedItems.stream().filter(TodoItem::isDone).collect(Collectors.toList());
        view.remove(doneItems);
        addedItems.removeAll(doneItems);
    }

    class MenuItem implements LayoutMenuItem {

        private final String icon;
        private final String text;
        private final SelectHandler selectHandler;

        MenuItem(String icon, String text, SelectHandler selectHandler) {
            this.icon = icon;
            this.text = text;
            this.selectHandler = selectHandler;
        }

        @Override
        public String icon() {
            return icon;
        }

        @Override
        public String text() {
            return text;
        }

        @Override
        public SelectHandler selectHandler() {
            return selectHandler;
        }
    }
}